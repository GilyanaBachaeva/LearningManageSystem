package com.example.LearningManageSystem.service;

import com.example.LearningManageSystem.dao.StudentRepository;
import com.example.LearningManageSystem.dto.StudentDTO;
import com.example.LearningManageSystem.exception.StudentNotFoundException;
import com.example.LearningManageSystem.mapper.StudentMapper;
import com.example.LearningManageSystem.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = studentMapper.map(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.map(savedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student with id " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found."));

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        Student updatedStudent = studentRepository.save(student);
        return studentMapper.map(updatedStudent);
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found."));
        return studentMapper.map(student);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::map)
                .collect(Collectors.toList());
    }
}
