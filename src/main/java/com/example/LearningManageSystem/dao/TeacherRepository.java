package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
