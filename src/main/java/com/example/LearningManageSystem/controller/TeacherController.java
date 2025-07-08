package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    final TeacherService teacherService;

    public ResponseEntity<TeacherDTO> addTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        log.info("Request to add teacher: {}", teacherDTO);
        TeacherDTO createdTeacher = teacherService.addTeacher(teacherDTO);
        log.info("Teacher added successfully: {}", createdTeacher);
        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        log.info("Request to delete teacher with id: {}", id);
        teacherService.deleteTeacher(id);
        log.info("Teacher with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO) {
        log.info("Request to update teacher with id: {}", id);
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        log.info("Teacher with id {} updated successfully: {}", id, updatedTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        log.info("Request to fetch teacher with id: {}", id);
        TeacherDTO teacher = teacherService.getTeacherById(id);
        log.info("Teacher found: {}", teacher);
        return ResponseEntity.ok(teacher);
    }
}
