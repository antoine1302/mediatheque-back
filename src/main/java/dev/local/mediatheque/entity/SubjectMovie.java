package dev.local.mediatheque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubjectMovie {

    @EmbeddedId
    private SubjectMovieKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonIgnore
    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonIgnore
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    public SubjectMovie(){}

    public SubjectMovie(SubjectMovieKey id, Movie movie, Subject subject, Role role) {
        this.id = id;
        this.movie = movie;
        this.subject = subject;
        this.role = role;
    }

    public SubjectMovieKey getId() {
        return id;
    }

    public void setId(SubjectMovieKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
