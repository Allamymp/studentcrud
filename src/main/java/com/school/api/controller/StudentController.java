package com.school.api.controller;

import com.school.api.model.DTO.StudentDTO;
import com.school.api.model.entities.Student;
import com.school.api.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentServices studentServices;

    @GetMapping("/listStudents")
    public ResponseEntity<?> listStudents() {
        try {
            List<Student> students = studentServices.listStudents();

            if (students.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Optional<Student> studentOptional = studentServices.searchStudentById(id);

            if (studentOptional.isPresent()) {
                return ResponseEntity.ok(studentOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO studentDTO, UriComponentsBuilder uriBuilder) {
        try {
            Student student = new Student(studentDTO);
            Student addedStudent = studentServices.addStudent(student);

            // URI builder
            UriComponents uriComponents = uriBuilder.path("/students/findById/{id}").buildAndExpand(addedStudent.getId());
            String uri = uriComponents.toUriString();

            return ResponseEntity.created(URI.create(uri)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        try {
            Optional<Student> existingStudent = studentServices.searchStudentById(id);

            if (existingStudent.isPresent()) {
                Student student = new Student(studentDTO);
                studentServices.updateStudent(id, student);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            if (studentServices.deleteStudent(id)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}