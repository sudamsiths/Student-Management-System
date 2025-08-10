package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.CoursesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController {

    final CoursesService coursesService;

    public void getAllCourses(){
        coursesService.getAllCourses();
    }
}
