package com.example.LearningManageSystem.mapper;

import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.model.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO map(StudentEntity student);
    StudentEntity map(StudentDTO studentDTO);
}
