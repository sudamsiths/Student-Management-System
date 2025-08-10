package org.example.repository;

import org.example.entity.AdminEntity;
import org.example.utill.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity , Long> {
    AdminEntity findByRole(UserRole userRole);

    Optional<AdminEntity> findByEmail(String email);
}
