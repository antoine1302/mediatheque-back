package dev.local.mediatheque.controller;

import dev.local.mediatheque.entity.Genre;
import dev.local.mediatheque.exception.ResourceNotFoundException;
import dev.local.mediatheque.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @CrossOrigin
    @GetMapping("/genres")
    public Page<Genre> getGenres(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @CrossOrigin
    @GetMapping("/genres/{genreId}")
    public Genre getGenre(@PathVariable Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));
    }

    @CrossOrigin
    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @CrossOrigin
    @PutMapping("/genres/{genreId}")
    public Genre updateGenre(
            @PathVariable Long genreId,
            @Valid @RequestBody Genre genreRequest
    ) {
        return genreRepository.findById(genreId)
                .map(genre -> {
                    genre.setTitle(genreRequest.getTitle());
                    genre.setDescription(genreRequest.getDescription());
                    genre.setCode(genreRequest.getCode());
                    genre.setEnabled(genreRequest.getEnabled());
                    genre.setWeight(genreRequest.getWeight());
                    return genreRepository.save(genre);
                }).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));
    }

    @CrossOrigin
    @DeleteMapping("/genres/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long genreId) {
        return genreRepository.findById(genreId)
                .map(genre -> {
                    genreRepository.delete(genre);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Genre not found with id " + genreId));
    }
}
