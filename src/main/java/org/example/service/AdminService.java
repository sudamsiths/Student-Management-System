package org.example.service;

import org.example.DTO.AdminDTO;

public interface AdminService {
    void createAdmin( AdminDTO adminDTO);

    AdminDTO loginAdmin(String email, String password);
}
