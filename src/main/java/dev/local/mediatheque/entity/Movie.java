package dev.local.mediatheque.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    private Long duration;

    @Column(columnDefinition = "text")
    private String synopsis;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date releaseDate;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private Set<SubjectMovie> subjectMovies;

    public Movie(){}

    public Movie(@NotBlank @Size(min = 1, max = 200) String title, Long duration, String synopsis, Date releaseDate, Set<SubjectMovie> subjectMovies) {
        this.title = title;
        this.duration = duration;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
        this.subjectMovies = subjectMovies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<SubjectMovie> getSubjectMovies() {
        return subjectMovies;
    }

    public void setSubjectMovies(Set<SubjectMovie> subjectMovies) {
        this.subjectMovies = subjectMovies;
    }
}
