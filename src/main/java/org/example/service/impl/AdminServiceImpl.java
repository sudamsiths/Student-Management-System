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
    public void createAdmin( AdminDTO adminDTO) {
        AdminEntity adminEntity = modelMapper.map(adminDTO, AdminEntity.class);
        adminRepository.save(adminEntity);
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

    @Override
    public void UpdateAdmin(Long id, AdminDTO adminDTO) {
        Optional<AdminEntity> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            AdminEntity adminEntity = optionalAdmin.get();
            adminEntity.setName(adminDTO.getName());
            adminEntity.setEmail(adminDTO.getEmail());
            adminEntity.setPassword(adminDTO.getPassword());
            adminRepository.save(adminEntity);
        } else {
            System.out.println("Admin not found with id: " + id);
        }
    }
}
