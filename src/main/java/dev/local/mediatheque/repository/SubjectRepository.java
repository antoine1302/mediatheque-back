package dev.local.mediatheque.repository;

import dev.local.mediatheque.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Page<Subject> findAllByDeletedAtNull(Pageable pageable);
}
