package dev.local.mediatheque.controller;

import dev.local.mediatheque.entity.*;
import dev.local.mediatheque.exception.ResourceNotFoundException;
import dev.local.mediatheque.repository.MovieRepository;
import dev.local.mediatheque.repository.RoleRepository;
import dev.local.mediatheque.repository.SubjectMovieRepository;
import dev.local.mediatheque.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SubjectMovieRepository subjectMovieRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin
    @GetMapping("/movies")
    public Page<Movie> getMovies(Pageable pageable) {
        return movieRepository.findAllByDeletedAtNull(pageable);
    }

    @CrossOrigin
    @GetMapping("/movies/{movieId}")
    public Movie getMovie(@PathVariable Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));
    }

    @CrossOrigin
    @Transactional
    @PostMapping("/movies")
    public Movie createMovie(@Valid @RequestBody Movie movie) {

        movie = movieRepository.save(movie);

        Set<SubjectMovie> subjectMovies = movie.getSubjectMovies();
        for (SubjectMovie subjectMovie : subjectMovies) {
            SubjectMovieKey subjectMovieKey = subjectMovie.getId();
            subjectMovieKey.setMovieId(movie.getId());
            subjectMovie.setId(subjectMovieKey);
            subjectMovie.setMovie(movie);

            Long subjectId = subjectMovieKey.getSubjectId();
            Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
            subjectMovie.setSubject(subject);

            Long roleId = subjectMovieKey.getRoleId();
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
            subjectMovie.setRole(role);

            subjectMovieRepository.save(subjectMovie);
        }

        return movie;
    }

    @Transactional
    @CrossOrigin
    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(
            @PathVariable Long movieId,
            @Valid @RequestBody Movie movieRequest
    ) {
        Movie movieUpdated = movieRepository
                .findById(movieId)
                .map(movie -> {
                    movie.setTitle(movieRequest.getTitle());
                    movie.setDuration(movieRequest.getDuration());
                    movie.setSynopsis(movieRequest.getSynopsis());
                    movie.setReleaseDate(movieRequest.getReleaseDate());
                    return movieRepository.save(movie);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));

        List<SubjectMovie> subjectMovieListToDel = subjectMovieRepository.findAllByMovie(movieUpdated);
        subjectMovieRepository.deleteAll(subjectMovieListToDel);

        Set<SubjectMovie> subjectMovieListToAdd = movieRequest.getSubjectMovies();
        for (SubjectMovie subjectmovie : subjectMovieListToAdd) {
            subjectmovie.getId().setMovieId(movieUpdated.getId());
            subjectmovie.setMovie(movieUpdated);

            Role role = roleRepository.findById(subjectmovie.getId().getRoleId()).orElseThrow(() -> new ResourceNotFoundException(""));
            subjectmovie.setRole(role);

            Subject subject = subjectRepository.findById(subjectmovie.getId().getSubjectId()).orElseThrow(() -> new ResourceNotFoundException(""));
            subjectmovie.setSubject(subject);
        }
        subjectMovieRepository.saveAll(subjectMovieListToAdd);

        return movieUpdated;
    }

    @CrossOrigin
    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId) {
        return movieRepository.findById(movieId)
                .map(movie -> {
                    movie.setDeletedAt(new Date());
                    movieRepository.save(movie);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id " + movieId));
    }
}
