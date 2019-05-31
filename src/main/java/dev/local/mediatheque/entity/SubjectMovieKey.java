package dev.local.mediatheque.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubjectMovieKey implements Serializable {
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "subject_id")
    private Long subjectId;
    @Column(name = "role_id")
    private Long roleId;

    public SubjectMovieKey(){}

    public SubjectMovieKey(Long movieId, Long subjectId, Long roleId) {
        this.movieId = movieId;
        this.subjectId = subjectId;
        this.roleId = roleId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectMovieKey that = (SubjectMovieKey) o;
        return movieId.equals(that.movieId) &&
                subjectId.equals(that.subjectId) &&
                roleId.equals(that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, subjectId, roleId);
    }
}
