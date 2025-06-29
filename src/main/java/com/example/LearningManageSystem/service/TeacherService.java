package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.TeacherRepository;
import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.exception.TeacherNotFoundException;
import com.example.LearningManageSystem.mapper.TeacherMapper;
import com.example.LearningManageSystem.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.map(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.map(savedTeacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException("Teacher with id " + id + " not found.");
        }
        teacherRepository.deleteById(id);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + id + " not found."));

        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return teacherMapper.map(updatedTeacher);
    }

    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with id " + id + " not found."));

        return teacherMapper.map(teacher);
    }
}
