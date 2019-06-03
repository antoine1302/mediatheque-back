package dev.local.mediatheque.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractReference extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(unique = true, nullable = false)
    private String code;

    @Column(columnDefinition = "boolean default true", nullable = false)
    private Boolean enabled = true;

    @Column(columnDefinition = "integer default 100", nullable = false)
    private Integer weight = 100;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deleted_at")
    private Date deletedAt;

    public AbstractReference(){}

    public AbstractReference(@NotBlank @Size(min = 1, max = 100) String title, String description, @NotBlank @Size(min = 1, max = 50) String code, Boolean enabled, Integer weight) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.enabled = enabled;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
