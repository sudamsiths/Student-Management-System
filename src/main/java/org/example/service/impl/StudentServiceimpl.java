package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.example.repository.CoursesRepository;
import org.example.entity.CoursesEntity;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final CoursesRepository coursesRepository;

    @Override
    public void getAllStudents() {
        studentRepository.findAll();
    }

    @Override
    public StudentEntity addStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity addStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        if (studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
            HashSet<CoursesEntity> courses = new HashSet<>();
            for (Long courseId : studentDTO.getCourseIds()) {
                coursesRepository.findById(courseId).ifPresent(courses::add);
            }
            studentEntity.setCourses(courses);
        }
        return studentRepository.save(studentEntity);
    }


}
