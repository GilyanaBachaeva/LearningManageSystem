package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Teachers")
public class TeacherController {

    final TeacherService teacherService;

    @PostMapping
    @ApiOperation(value = "Add Teacher", notes = "Creates a new teacher")
    public ResponseEntity<TeacherDTO> addTeacher(@ApiParam(value = "Teachers data to be added", required = true)
                                                 @Valid @RequestBody TeacherDTO teacherDTO) {
        log.info("Request to add teacher: {}", teacherDTO);
        TeacherDTO createdTeacher = teacherService.addTeacher(teacherDTO);
        log.info("Teacher added successfully: {}", createdTeacher);
        return ResponseEntity.ok(createdTeacher);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Teacher", notes = "Deletes a teacher by its ID")
    public ResponseEntity<Void> deleteTeacher(@ApiParam(value = "Teacher ID", required = true)
                                              @PathVariable Long id) {
        log.info("Request to delete teacher with id: {}", id);
        teacherService.deleteTeacher(id);
        log.info("Teacher with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Teacher", notes = "Updates the information of a teacher by its ID")
    public ResponseEntity<TeacherDTO> updateTeacher(@ApiParam(value = "Teacher ID", required = true) @PathVariable Long id,
                                                    @ApiParam(value = "Updated teacher data", required = true)
                                                    @Valid @RequestBody TeacherDTO teacherDTO) {
        log.info("Request to update teacher with id: {}", id);
        TeacherDTO updatedTeacher = teacherService.updateTeacher(id, teacherDTO);
        log.info("Teacher with id {} updated successfully: {}", id, updatedTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Teacher by ID", notes = "Returns information about a teacher by its ID")
    public ResponseEntity<TeacherDTO> getTeacherById(@ApiParam(value = "Teacher ID", required = true)
                                                     @PathVariable Long id) {
        log.info("Request to fetch teacher with id: {}", id);
        TeacherDTO teacher = teacherService.getTeacherById(id);
        log.info("Teacher found: {}", teacher);
        return ResponseEntity.ok(teacher);
    }
}
