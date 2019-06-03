package dev.local.mediatheque.controller;

import dev.local.mediatheque.entity.Subject;
import dev.local.mediatheque.exception.ResourceNotFoundException;
import dev.local.mediatheque.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @CrossOrigin
    @GetMapping("/subjects")
    public Page<Subject> getSubjects(Pageable pageable) {
        return subjectRepository.findAllByDeletedAtNull(pageable);
    }

    @CrossOrigin
    @GetMapping("/subjects/{subjectId}")
    public Subject getSubject(@PathVariable Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
    }

    @CrossOrigin
    @PostMapping("/subjects")
    public Subject createSubject(@Valid @RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @CrossOrigin
    @PutMapping("/subjects/{subjectId}")
    public Subject updateSubject(
            @PathVariable Long subjectId,
            @Valid @RequestBody Subject subjectRequest
    ) {
        return subjectRepository.findById(subjectId)
                .map(subject -> {
                    subject.setFirstname(subjectRequest.getFirstname());
                    subject.setLastname(subjectRequest.getLastname());
                    subject.setDateOfBirth(subjectRequest.getDateOfBirth());
                    subject.setCitizenship(subjectRequest.getCitizenship());
                    subject.setPlaceOfBirth(subjectRequest.getPlaceOfBirth());
                    return subjectRepository.save(subject);
                }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
    }

    @CrossOrigin
    @DeleteMapping("/subjects/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) {
        return subjectRepository.findById(subjectId)
                .map(subject -> {
                    subject.setDeletedAt(new Date());
                    subjectRepository.save(subject);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + subjectId));
    }
}
