package org.example.service;

import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    void getAllStudents();

    StudentEntity addStudent(StudentEntity studentEntity);
}
