package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Students")
public class StudentController {

    final StudentService studentService;

    @PostMapping
    @ApiOperation(value = "Add Student", notes = "Creates a new student")
    public ResponseEntity<StudentDTO> addStudent(@ApiParam(value = "Students data to be added", required = true)
                                                 @Valid @RequestBody StudentDTO studentDTO) {
        log.info("Request to add student: {}", studentDTO);
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        log.info("Student added successfully: {}", createdStudent);
        return ResponseEntity.ok(createdStudent);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Student", notes = "Deletes a student by its ID")
    public ResponseEntity<Void> deleteStudent(@ApiParam(value = "Student ID", required = true)
                                              @PathVariable Long id) {
        log.info("Request to delete student with id: {}", id);
        studentService.deleteStudent(id);
        log.info("Student with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Student", notes = "Updates the information of a student by its ID")
    public ResponseEntity<StudentDTO> updateStudent(@ApiParam(value = "Student ID", required = true) @PathVariable Long id,
                                                    @ApiParam(value = "Updated student data", required = true)
                                                    @Valid @RequestBody StudentDTO studentDTO) {
        log.info("Request to update student with id: {}", id);
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        log.info("Student with id {} updated successfully: {}", id, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Student by ID", notes = "Returns information about a student by its ID")
    public ResponseEntity<StudentDTO> getStudentById(@ApiParam(value = "Student ID", required = true)
                                                     @PathVariable Long id) {
        log.info("Request to fetch student with id: {}", id);
        StudentDTO student = studentService.getStudentById(id);
        log.info("Student found: {}", student);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    @ApiOperation(value = "Get All Students", notes = "Returns a list of all students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        log.info("Request to get all students");
        List<StudentDTO> students = studentService.getAllStudents();
        log.info("Number of students found: {}", students.size());
        return ResponseEntity.ok(students);
    }
}
