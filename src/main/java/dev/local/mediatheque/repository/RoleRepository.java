package dev.local.mediatheque.repository;

import dev.local.mediatheque.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r from Role r where r.enabled = true and r.deletedAt is null order by weight")
    List<Role> getAllActiveRole();

    Page<Role> findAllByDeletedAtNull(Pageable pageable);
}
