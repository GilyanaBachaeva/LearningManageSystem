package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.model.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherDTO map(Teacher teacher);
    Teacher map(TeacherDTO teacherDTO);
}
