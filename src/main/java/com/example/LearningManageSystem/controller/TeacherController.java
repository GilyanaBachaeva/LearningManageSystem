package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    final TeacherService teacherService;

    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.addTeacher(teacherDTO);
        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }
}
