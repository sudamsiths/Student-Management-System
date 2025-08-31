package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AttendanceDTO;
import org.example.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO result = attendanceService.markAttendance(attendanceDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceForStudent(@PathVariable Long studentId) {
        List<AttendanceDTO> records = attendanceService.getAttendanceForStudent(studentId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> records = attendanceService.getAllAttendance();
        return ResponseEntity.ok(records);
    }
}

