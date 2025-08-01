package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
