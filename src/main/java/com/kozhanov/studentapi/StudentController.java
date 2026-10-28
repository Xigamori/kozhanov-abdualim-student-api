package com.kozhanov.studentapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentRepository repository;
    private final String studentName;

    public StudentController(StudentRepository repository,
                             @Value("${app.student.name}") String studentName) {
        this.repository = repository;
        this.studentName = studentName;
    }

    @GetMapping("/")
    public String home() {
        return "Student API by " + studentName;
    }

    @GetMapping("/students")
    public List<Student> getAll() {
        return repository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Optional<Student> student = repository.findById(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student.get());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = repository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
