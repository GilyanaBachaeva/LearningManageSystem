package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        log.info("Request to add course: {}", courseDTO);
        CourseDTO createdCourse = courseService.addCourse(courseDTO);
        log.info("Course added successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping("/createWithSchedule")
    public ResponseEntity<CourseDTO> createCourseWithSchedule(@Valid @RequestBody CourseDTO courseDTO, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to create course with schedule: {}", courseDTO);
        CourseDTO createdCourse = courseService.createCourseWithSchedule(courseDTO, scheduleDTO);
        log.info("Course with schedule created successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("Request to delete course with id: {}", id);
        courseService.deleteCourse(id);
        log.info("Course with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        log.info("Request to update course with id: {}", id);
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        log.info("Course with id {} updated successfully: {}", id, updatedCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        log.info("Request to fetch course with id: {}", id);
        CourseDTO course = courseService.getCourseById(id);
        log.info("Course found: {}", course);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("Request to get all courses");
        List<CourseDTO> courses = courseService.getAllCourses();
        log.info("Number of courses found: {}", courses.size());
        return ResponseEntity.ok(courses);
    }
}
