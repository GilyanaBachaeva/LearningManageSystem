package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
