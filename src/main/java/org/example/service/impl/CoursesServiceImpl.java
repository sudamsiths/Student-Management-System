package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.CoursesDTO;
import org.example.entity.CoursesEntity;
import org.example.repository.CoursesRepository;
import org.example.service.CoursesService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoursesServiceImpl implements CoursesService  {

    private final CoursesRepository courseRepository;

    @Override
    public CoursesDTO addCourse(CoursesDTO courseDTO) {
        CoursesEntity coursesEntity = new CoursesEntity();
        coursesEntity.setName(courseDTO.getName());
        coursesEntity.setDuration(courseDTO.getDuration());
        coursesEntity.setStartDate(courseDTO.getStartDate());

        CoursesEntity savedCourse = courseRepository.save(coursesEntity);

        return new CoursesDTO(
                savedCourse.getId(),
                savedCourse.getName(),
                savedCourse.getDuration(),
                savedCourse.getStartDate()
        );
    }

    @Override
    public Object getAllCourses() {
        return courseRepository.findAll();
    }
}
