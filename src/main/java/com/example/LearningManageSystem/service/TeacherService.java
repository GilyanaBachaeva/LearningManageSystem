package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.TeacherRepository;
import com.example.LearningManageSystem.dto.TeacherDTO;
import com.example.LearningManageSystem.exception.TeacherNotFoundException;
import com.example.LearningManageSystem.mapper.TeacherMapper;
import com.example.LearningManageSystem.model.TeacherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);

    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        logger.info("Adding teacher: {}", teacherDTO);
        TeacherEntity teacher = teacherMapper.map(teacherDTO);
        TeacherEntity savedTeacher = teacherRepository.save(teacher);
        logger.info("Teacher added successfully: {}", savedTeacher);
        return teacherMapper.map(savedTeacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existent teacher with id: {}", id);
            throw new TeacherNotFoundException("Teacher with id " + id + " not found.");
        }
        logger.info("Deleting teacher with id: {}", id);
        teacherRepository.deleteById(id);
        logger.info("Teacher with id {} deleted successfully.", id);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        logger.info("Updating teacher with id: {}", id);
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Teacher with id {} not found for update.", id);
                    return new TeacherNotFoundException("Teacher with id " + id + " not found.");
                });

        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        TeacherEntity updatedTeacher = teacherRepository.save(teacher);
        logger.info("Teacher with id {} updated successfully: {}", id, updatedTeacher);
        return teacherMapper.map(updatedTeacher);
    }

    public TeacherDTO getTeacherById(Long id) {
        logger.info("Fetching teacher with id: {}", id);
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Teacher with id {} not found.", id);
                    return new TeacherNotFoundException("Teacher with id " + id + " not found.");
                });
        logger.info("Teacher found: {}", teacher);
        return teacherMapper.map(teacher);
    }
}
