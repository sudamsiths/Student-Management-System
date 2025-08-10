package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AdminDTO;
import org.example.entity.AdminEntity;
import org.example.repository.AdminRepository;
import org.example.service.AdminService;
import org.example.utill.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    final ModelMapper modelMapper;
    final AdminRepository adminRepository;


    @Override
    public void createAdmin() {
        AdminEntity optionalAdmin = adminRepository.findByRole(UserRole.ADMIN);
        if (optionalAdmin==null){
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setName("Admin");
            adminEntity.setEmail("admin123@gmail.com");
            adminEntity.setPassword("admin123");
            adminEntity.setRole(UserRole.ADMIN);
            adminRepository.save(adminEntity);
            System.out.println("Admin user created successfully");
        }else {
            System.out.println("Admin user already exists");
        }
    }

    @Override
    public AdminDTO loginAdmin(String email, String password) {
        Optional<AdminEntity> adminEntity = adminRepository.findByEmail(email);
        if (adminEntity.isPresent()) {
            if (adminEntity.get().getPassword().equals(password)) {
                return modelMapper.map(adminEntity.get(), AdminDTO.class);
            } else {
                System.out.println("Invalid password for email: " + email);
                return null;
            }
        } else {
            System.out.println("User not found with email: " + email);
            return null;
        }
    }
}
