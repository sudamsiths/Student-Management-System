package org.example.service;

import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    void getAllStudents();

    StudentEntity addStudent(StudentEntity studentEntity);

    StudentEntity addStudent(StudentDTO studentDTO);

    void deleteStudent(Long id);

    StudentEntity updateStudent(Long id, StudentDTO studentDTO);
}
