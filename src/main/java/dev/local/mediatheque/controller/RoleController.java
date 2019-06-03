package dev.local.mediatheque.controller;

import dev.local.mediatheque.entity.Role;
import dev.local.mediatheque.exception.ResourceNotFoundException;
import dev.local.mediatheque.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @CrossOrigin
    @GetMapping("/roles/list")
    public List<Role> getRolesAsKeyValue() {
        return roleRepository.getAllActiveRole();
    }

    @CrossOrigin
    @GetMapping("/roles")
    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAllByDeletedAtNull(pageable);
    }

    @CrossOrigin
    @GetMapping("/roles/{roleId}")
    public Role getRole(@PathVariable Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }

    @CrossOrigin
    @PostMapping("/roles")
    public Role createRole(@Valid @RequestBody Role role) {

        return roleRepository.save(role);
    }

    @CrossOrigin
    @PutMapping("/roles/{roleId}")
    public Role updateRole(
            @PathVariable Long roleId,
            @Valid @RequestBody Role roleRequest
    ) {
        return roleRepository.findById(roleId)
                .map(role -> {
                    role.setTitle(roleRequest.getTitle());
                    role.setDescription(roleRequest.getDescription());
                    role.setCode(roleRequest.getCode());
                    role.setEnabled(roleRequest.getEnabled());
                    role.setWeight(roleRequest.getWeight());
                    return roleRepository.save(role);
                }).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }

    @CrossOrigin
    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        return roleRepository.findById(roleId)
                .map(role -> {
                    role.setDeletedAt((new Date()));
                    roleRepository.save(role);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }
}
