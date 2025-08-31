package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void getAllStudents() {
        studentRepository.findAll();
    }

    @Override
    public StudentEntity addStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }


}
