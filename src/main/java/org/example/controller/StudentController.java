package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;
import org.example.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public void getAllStudents() {
        studentService.getAllStudents();
    }

    @PostMapping("/add")
    public StudentEntity addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }
}
