package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.model.Student;

public interface StudentMapper {
    StudentDTO map(Student student);
    Student map(StudentDTO studentDTO);
}
