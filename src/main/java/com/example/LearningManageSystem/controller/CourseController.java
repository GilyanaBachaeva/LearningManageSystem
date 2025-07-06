package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        logger.info("Request to add course: {}", courseDTO);
        CourseDTO createdCourse = courseService.addCourse(courseDTO);
        logger.info("Course added successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping("/createWithSchedule")
    public ResponseEntity<CourseDTO> createCourseWithSchedule(@Valid @RequestBody CourseDTO courseDTO, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        logger.info("Request to create course with schedule: {}", courseDTO);
        CourseDTO createdCourse = courseService.createCourseWithSchedule(courseDTO, scheduleDTO);
        logger.info("Course with schedule created successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        logger.info("Request to delete course with id: {}", id);
        courseService.deleteCourse(id);
        logger.info("Course with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        logger.info("Request to update course with id: {}", id);
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        logger.info("Course with id {} updated successfully: {}", id, updatedCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        logger.info("Request to fetch course with id: {}", id);
        CourseDTO course = courseService.getCourseById(id);
        logger.info("Course found: {}", course);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        logger.info("Request to get all courses");
        List<CourseDTO> courses = courseService.getAllCourses();
        logger.info("Number of courses found: {}", courses.size());
        return ResponseEntity.ok(courses);
    }
}
