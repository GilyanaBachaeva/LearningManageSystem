package com.example.LearningManageSystem.dao;

import com.example.LearningManageSystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
