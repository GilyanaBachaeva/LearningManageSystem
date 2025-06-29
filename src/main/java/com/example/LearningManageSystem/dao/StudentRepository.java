package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
