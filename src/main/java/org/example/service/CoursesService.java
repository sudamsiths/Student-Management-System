package org.example.service;

import org.example.DTO.CoursesDTO;

import java.util.List;

public interface CoursesService {
    CoursesDTO addCourse(CoursesDTO courseDTO);

    List<CoursesDTO> getAllCourses();
}
