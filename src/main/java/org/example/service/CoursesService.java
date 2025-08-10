package org.example.service;

import org.example.DTO.CoursesDTO;

public interface CoursesService {
    CoursesDTO addCourse(CoursesDTO courseDTO);

    Object getAllCourses();
}
