package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.CoursesDTO;
import org.example.service.CoursesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@CrossOrigin
public class CoursesController {

    final CoursesService coursesService;


    @PostMapping("/add/course")
    public ResponseEntity<?>addCource(@RequestBody CoursesDTO courseDTO) {
        try {
           return ResponseEntity.ok(coursesService.addCourse(courseDTO));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding course: " + e.getMessage());
        }
    }
    @GetMapping("/api/getAllCourses")
    public void getAllCourses() {
        coursesService.getAllCourses();
    }
}
