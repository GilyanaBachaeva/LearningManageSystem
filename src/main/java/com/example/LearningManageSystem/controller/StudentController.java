package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        logger.info("Request to add student: {}", studentDTO);
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        logger.info("Student added successfully: {}", createdStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        logger.info("Request to delete student with id: {}", id);
        studentService.deleteStudent(id);
        logger.info("Student with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        logger.info("Request to update student with id: {}", id);
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        logger.info("Student with id {} updated successfully: {}", id, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        logger.info("Request to fetch student with id: {}", id);
        StudentDTO student = studentService.getStudentById(id);
        logger.info("Student found: {}", student);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        logger.info("Request to get all students");
        List<StudentDTO> students = studentService.getAllStudents();
        logger.info("Number of students found: {}", students.size());
        return ResponseEntity.ok(students);
    }
}
