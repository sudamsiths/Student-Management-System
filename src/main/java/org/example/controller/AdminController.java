package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AdminDTO;
import org.example.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AdminController {

    final AdminService adminService;

    @PostMapping("/create")
    public void createAdmin(@RequestBody AdminDTO adminDTO) {
        adminService.createAdmin(adminDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDTO loginRequest) {
        AdminDTO adminDTO = adminService.loginAdmin(loginRequest.getEmail(), loginRequest.getPassword());
        if (adminDTO != null) {
            return ResponseEntity.ok().body(adminDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        adminService.UpdateAdmin(id, adminDTO);
        return ResponseEntity.ok("Admin updated successfully");
    }
}