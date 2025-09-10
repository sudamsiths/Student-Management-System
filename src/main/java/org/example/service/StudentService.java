package org.example.service;

import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    StudentDTO addStudent(StudentDTO studentDTO);
    void deleteStudent(Long id);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
}
