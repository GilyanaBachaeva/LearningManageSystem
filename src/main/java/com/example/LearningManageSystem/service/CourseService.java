package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.CourseRepository;
import com.example.LearningManageSystem.dao.ScheduleRepository;
import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.dto.ScheduleDTO;
import com.example.LearningManageSystem.exception.CourseNotFoundException;
import com.example.LearningManageSystem.mapper.CourseMapper;
import com.example.LearningManageSystem.model.CourseEntity;
import com.example.LearningManageSystem.model.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ScheduleRepository scheduleRepository;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public CourseDTO addCourse(CourseDTO courseDTO) {
        logger.info("Adding group: {}", courseDTO);
        CourseEntity course = courseMapper.map(courseDTO);
        CourseEntity savedCourse = courseRepository.save(course);
        logger.info("Course added successfully: {}", savedCourse);
        return courseMapper.map(savedCourse);
    }

    @Transactional
    public CourseDTO createCourseWithSchedule(CourseDTO courseDTO, ScheduleDTO scheduleDTO) {
        logger.info("Creating course with name: {}", courseDTO.getName());

        CourseEntity course = new CourseEntity();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        CourseEntity savedCourse = courseRepository.save(course);
        logger.info("Course created successfully with id: {}", savedCourse.getId());

        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setCourse(savedCourse);
        schedule.setDate(scheduleDTO.getDate());

        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
        logger.info("Schedule created successfully for course id: {} with schedule id: {}", savedCourse.getId(), savedSchedule.getId());

        courseDTO.setId(savedCourse.getId());
        courseDTO.setScheduleIds(Set.of(savedSchedule.getId()));

        logger.info("Returning CourseDTO with id: {} and schedule id: {}", courseDTO.getId(), courseDTO.getScheduleIds());
        return courseDTO;
    }

    public void deleteCourse(Long id) {
        logger.info("Request to delete course with ID: {}", id);
        if (!courseRepository.existsById(id)) {
            logger.error("Course with ID {} not found for deleting", id);
            throw new CourseNotFoundException("Course with id " + id + " not found.");
        }
        logger.info("Course with ID {} has been deleted.", id);
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        logger.info("Request to update course with ID: {}", id);
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Course with ID {} not found for updating", id);
                    return new CourseNotFoundException("Course with id " + id + " not found.");
                });

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        CourseEntity updatedCourse = courseRepository.save(course);
        logger.info("Course with ID {} has been updated.", id);
        return courseMapper.map(updatedCourse);
    }

    public CourseDTO getCourseById(Long id) {
        logger.info("Request to get course with ID: {}", id);
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Course with ID {} not found.", id);
                    return new CourseNotFoundException("Course with id " + id + " not found.");
                });
        logger.info("Course with ID {} found.", id);
        return courseMapper.map(course);
    }

    public List<CourseDTO> getAllCourses() {
        logger.info("Request to get all courses");
        List<CourseDTO> courses = courseRepository.findAll().stream()
                .map(courseMapper::map)
                .collect(Collectors.toList());
        logger.info("Number of courses found: {}", courses.size());
        return courses;
    }
}
