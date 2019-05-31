package dev.local.mediatheque.repository;

import dev.local.mediatheque.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r from Role r where r.enabled = true order by weight")
    List<Role> getAllActiveRole();
}
