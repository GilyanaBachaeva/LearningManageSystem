package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDTO teacherToTeacherDTO(Teacher teacher);
    Teacher teacherDTOToTeacher(TeacherDTO teacherDTO);
}
