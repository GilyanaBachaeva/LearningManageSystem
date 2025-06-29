package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.model.Course;

public interface CourseMapper {
    CourseDTO map(Course course);
    Course map(CourseDTO courseDTO);
}
