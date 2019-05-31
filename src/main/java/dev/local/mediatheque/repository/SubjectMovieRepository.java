package dev.local.mediatheque.repository;

import dev.local.mediatheque.entity.Movie;
import dev.local.mediatheque.entity.SubjectMovie;
import dev.local.mediatheque.entity.SubjectMovieKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMovieRepository extends JpaRepository<SubjectMovie, SubjectMovieKey> {
    List<SubjectMovie> findAllByMovie(Movie movie);
}
