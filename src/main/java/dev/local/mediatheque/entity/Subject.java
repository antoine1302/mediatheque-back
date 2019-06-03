package dev.local.mediatheque.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstname;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastname;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @NotBlank
    @Size(min = 1, max = 100)
    private String citizenship;

    @NotBlank
    @Size(min = 1, max = 100)
    private String placeOfBirth;

    @OneToMany(mappedBy = "subject")
    private Set<SubjectMovie> subjectMovies;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deleted_at")
    private Date deletedAt;

    public Subject(){}

    public Subject(@NotBlank @Size(min = 1, max = 100) String firstname, @NotBlank @Size(min = 1, max = 100) String lastname, Date dateOfBirth, @NotBlank @Size(min = 1, max = 100) String citizenship, @NotBlank @Size(min = 1, max = 100) String placeOfBirth, Set<SubjectMovie> subjectMovies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.placeOfBirth = placeOfBirth;
        this.subjectMovies = subjectMovies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<SubjectMovie> getSubjectMovies() {
        return subjectMovies;
    }

    public void setSubjectMovies(Set<SubjectMovie> subjectMovies) {
        this.subjectMovies = subjectMovies;
    }
}
