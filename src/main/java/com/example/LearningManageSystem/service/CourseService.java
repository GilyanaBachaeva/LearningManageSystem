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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ScheduleRepository scheduleRepository;

    public CourseDTO addCourse(CourseDTO courseDTO) {
        CourseEntity course = courseMapper.map(courseDTO);
        CourseEntity savedCourse = courseRepository.save(course);
        return courseMapper.map(savedCourse);
    }

    @Transactional
    public CourseDTO createCourseWithSchedule(CourseDTO courseDTO, ScheduleDTO scheduleDTO) {
        CourseEntity course = new CourseEntity();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        CourseEntity savedCourse = courseRepository.save(course);

        ScheduleEntity schedule = new ScheduleEntity();
        schedule.setCourse(savedCourse);
        schedule.setDate(scheduleDTO.getDate());

        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

        courseDTO.setId(savedCourse.getId());
        courseDTO.setScheduleIds(Set.of(savedSchedule.getId()));

        return courseDTO;
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course with id " + id + " not found.");
        }
        courseRepository.deleteById(id);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " not found."));

        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());

        CourseEntity updatedCourse = courseRepository.save(course);
        return courseMapper.map(updatedCourse);
    }

    public CourseDTO getCourseById(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + id + " not found."));
        return courseMapper.map(course);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::map)
                .collect(Collectors.toList());
    }
}
