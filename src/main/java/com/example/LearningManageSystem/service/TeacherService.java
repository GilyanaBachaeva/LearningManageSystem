package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.TeacherRepository;
import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.exception.TeacherNotFoundException;
import com.example.LearningManageSystem.mapper.TeacherMapper;
import com.example.LearningManageSystem.model.TeacherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        log.info("Adding teacher: {}", teacherDTO);
        TeacherEntity teacher = teacherMapper.map(teacherDTO);
        TeacherEntity savedTeacher = teacherRepository.save(teacher);
        log.info("Teacher added successfully: {}", savedTeacher);
        return teacherMapper.map(savedTeacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent teacher with id: {}", id);
            throw new TeacherNotFoundException("Teacher with id " + id + " not found.");
        }
        log.info("Deleting teacher with id: {}", id);
        teacherRepository.deleteById(id);
        log.info("Teacher with id {} deleted successfully.", id);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        log.info("Updating teacher with id: {}", id);
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Teacher with id {} not found for update.", id);
                    return new TeacherNotFoundException("Teacher with id " + id + " not found.");
                });

        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        TeacherEntity updatedTeacher = teacherRepository.save(teacher);
        log.info("Teacher with id {} updated successfully: {}", id, updatedTeacher);
        return teacherMapper.map(updatedTeacher);
    }

    public TeacherDTO getTeacherById(Long id) {
        log.info("Fetching teacher with id: {}", id);
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Teacher with id {} not found.", id);
                    return new TeacherNotFoundException("Teacher with id " + id + " not found.");
                });
        log.info("Teacher found: {}", teacher);
        return teacherMapper.map(teacher);
    }
}
