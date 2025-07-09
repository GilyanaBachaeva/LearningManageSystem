package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.model.TeacherEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherDTO map(TeacherEntity teacher);
    TeacherEntity map(TeacherDTO teacherDTO);
}
