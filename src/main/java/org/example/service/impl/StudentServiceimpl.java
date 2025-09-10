package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.StudentDTO;
import org.example.entity.StudentEntity;
import org.example.entity.CoursesEntity;
import org.example.repository.StudentRepository;
import org.example.repository.CoursesRepository;
import org.example.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CoursesRepository coursesRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return students.stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(studentDTO.getName());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setPassword(studentDTO.getPassword());

        // Handle course assignment with database validation
        if (studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
            Set<CoursesEntity> courses = new HashSet<>();

            for (Long courseId : studentDTO.getCourseIds()) {
                CoursesEntity course = coursesRepository.findById(courseId)
                        .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
                courses.add(course);
                System.out.println("Found and added course: " + course.getName() + " (ID: " + course.getId() + ")");
            }

            studentEntity.setCourses(courses);
            System.out.println("Total courses assigned: " + courses.size());
        }

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        System.out.println("Student saved with ID: " + savedStudent.getId());

        return mapToDTO(savedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(studentDTO.getName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPassword(studentDTO.getPassword());

        // Handle course assignment with database validation
        if (studentDTO.getCourseIds() != null && !studentDTO.getCourseIds().isEmpty()) {
            Set<CoursesEntity> courses = new HashSet<>();

            for (Long courseId : studentDTO.getCourseIds()) {
                CoursesEntity course = coursesRepository.findById(courseId)
                        .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
                courses.add(course);
            }

            existingStudent.setCourses(courses);
        } else {
            existingStudent.setCourses(new HashSet<>());
        }

        StudentEntity updatedStudent = studentRepository.save(existingStudent);
        return mapToDTO(updatedStudent);
    }

    private StudentDTO mapToDTO(StudentEntity student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setPassword(student.getPassword());

        // Map courses to courseIds with proper null checking
        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            Set<Long> courseIds = student.getCourses().stream()
                    .map(CoursesEntity::getId)
                    .collect(Collectors.toSet());
            dto.setCourseIds(courseIds);
            System.out.println("Mapped courseIds: " + courseIds);
        } else {
            System.out.println("No courses found for student: " + student.getName());
        }

        return dto;
    }
}
