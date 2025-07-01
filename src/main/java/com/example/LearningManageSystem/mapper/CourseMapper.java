package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.CourseDTO;
import com.example.LearningManageSystem.model.CourseEntity;

public interface CourseMapper {
    CourseDTO map(CourseEntity course);
    CourseEntity map(CourseDTO courseDTO);
}
