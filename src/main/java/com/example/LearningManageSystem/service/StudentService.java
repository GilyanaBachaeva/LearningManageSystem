package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.StudentRepository;
import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.exception.StudentNotFoundException;
import com.example.LearningManageSystem.mapper.StudentMapper;
import com.example.LearningManageSystem.model.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentDTO addStudent(StudentDTO studentDTO) {
        logger.info("Adding student: {}", studentDTO);
        StudentEntity student = studentMapper.map(studentDTO);
        StudentEntity savedStudent = studentRepository.save(student);
        logger.info("Teacher added successfully: {}", savedStudent);
        return studentMapper.map(savedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existent student with id: {}", id);
            throw new StudentNotFoundException("Student with id " + id + " not found.");
        }
        logger.info("Deleting student with id: {}", id);
        studentRepository.deleteById(id);
        logger.info("Student with id {} deleted successfully.", id);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        logger.info("Updating student with id: {}", id);
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student with id {} not found for update.", id);
                    return new StudentNotFoundException("Student with id " + id + " not found.");
                });

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        StudentEntity updatedStudent = studentRepository.save(student);
        logger.info("Student with id {} updated successfully: {}", id, updatedStudent);
        return studentMapper.map(updatedStudent);
    }

    public StudentDTO getStudentById(Long id) {
        logger.info("Fetching student with id: {}", id);
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student with id {} not found.", id);
                    return new StudentNotFoundException("Student with id " + id + " not found.");
                });
        logger.info("Student found: {}", student);
        return studentMapper.map(student);
    }

    public List<StudentDTO> getAllStudents() {
        logger.info("Fetching all students");
        List<StudentDTO> students = studentRepository.findAll().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
        logger.info("Total students fetched: {}", students.size());
        return students;
    }
}
