package org.example.repository;

import org.example.entity.CoursesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<CoursesEntity , Long> {
}
