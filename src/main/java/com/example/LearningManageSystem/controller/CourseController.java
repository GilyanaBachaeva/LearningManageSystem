package com.example.LearningManageSystem.controller;

import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.service.CourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
@Slf4j
@Api(tags = "Courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @ApiOperation(value = "Add Course", notes = "Creates a new course")
    public ResponseEntity<CourseDTO> addCourse(@ApiParam(value = "Courses data to be added", required = true)
                                               @Valid @RequestBody CourseDTO courseDTO) {
        log.info("Request to add course: {}", courseDTO);
        CourseDTO createdCourse = courseService.addCourse(courseDTO);
        log.info("Course added successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @PostMapping("/createWithSchedule")
    @ApiOperation(value = "Add Course with Schedule", notes = "Creates a new course with schedule")
    public ResponseEntity<CourseDTO> createCourseWithSchedule(@ApiParam(value = "Course data to be added", required = true)
                                                              @Valid @RequestBody CourseDTO courseDTO,
                                                              @ApiParam(value = "Schedule data to be added", required = true)
                                                              @Valid @RequestBody ScheduleDTO scheduleDTO) {
        log.info("Request to create course with schedule: {}", courseDTO);
        CourseDTO createdCourse = courseService.createCourseWithSchedule(courseDTO, scheduleDTO);
        log.info("Course with schedule created successfully: {}", createdCourse);
        return ResponseEntity.ok(createdCourse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Course", notes = "Deletes a course by its ID")
    public ResponseEntity<Void> deleteCourse(@ApiParam(value = "Course ID", required = true)
                                             @PathVariable Long id) {
        log.info("Request to delete course with id: {}", id);
        courseService.deleteCourse(id);
        log.info("Course with id {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Course", notes = "Updates the information of a course by its ID")
    public ResponseEntity<CourseDTO> updateCourse(@ApiParam(value = "Course ID", required = true) @PathVariable Long id,
                                                  @ApiParam(value = "Updated course data", required = true)
                                                  @Valid @RequestBody CourseDTO courseDTO) {
        log.info("Request to update course with id: {}", id);
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        log.info("Course with id {} updated successfully: {}", id, updatedCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Course by ID", notes = "Returns information about a course by its ID")
    public ResponseEntity<CourseDTO> getCourseById(@ApiParam(value = "Course ID", required = true)
                                                   @PathVariable Long id) {
        log.info("Request to fetch course with id: {}", id);
        CourseDTO course = courseService.getCourseById(id);
        log.info("Course found: {}", course);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    @ApiOperation(value = "Get All Courses", notes = "Returns a list of all courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("Request to get all courses");
        List<CourseDTO> courses = courseService.getAllCourses();
        log.info("Number of courses found: {}", courses.size());
        return ResponseEntity.ok(courses);
    }
}
