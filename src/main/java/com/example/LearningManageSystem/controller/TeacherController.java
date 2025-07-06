package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    final TeacherService teacherService;
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    public ResponseEntity<TeacherDTO> addTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        logger.info("Request to add teacher: {}", teacherDTO);
        TeacherDTO createdTeacher = teacherService.addTeacher(teacherDTO);
        logger.info("Teacher added successfully: {}", createdTeacher);
        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        logger.info("Request to delete teacher with id: {}", id);
        teacherService.deleteTeacher(id);
        logger.info("Teacher with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO) {
        logger.info("Request to update teacher with id: {}", id);
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        logger.info("Teacher with id {} updated successfully: {}", id, updatedTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        logger.info("Request to fetch teacher with id: {}", id);
        TeacherDTO teacher = teacherService.getTeacherById(id);
        logger.info("Teacher found: {}", teacher);
        return ResponseEntity.ok(teacher);
    }
}
