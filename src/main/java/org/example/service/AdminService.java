package org.example.service;

import org.example.DTO.AdminDTO;

public interface AdminService {
    void createAdmin();

    AdminDTO loginAdmin(String email, String password);
}
