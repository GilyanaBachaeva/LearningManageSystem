package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
