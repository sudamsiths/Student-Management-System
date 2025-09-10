package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AttendanceDTO;
import org.example.service.AttendanceService;
import org.example.utill.AttendanceStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/attendance")
@CrossOrigin
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO result = attendanceService.markAttendance(attendanceDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/mark-bulk")
    public ResponseEntity<List<AttendanceDTO>> markBulkAttendance(@RequestBody List<AttendanceDTO> attendanceDTOs) {
        List<AttendanceDTO> results = attendanceService.markBulkAttendance(attendanceDTOs);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO attendanceDTO) {
        AttendanceDTO result = attendanceService.updateAttendance(id, attendanceDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceForStudent(@PathVariable Long studentId) {
        List<AttendanceDTO> records = attendanceService.getAttendanceForStudent(studentId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/student/{studentId}/date-range")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceForStudentByDateRange(
            @PathVariable Long studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<AttendanceDTO> records = attendanceService.getAttendanceForStudentByDateRange(studentId, startDate, endDate);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AttendanceDTO> records = attendanceService.getAttendanceByDate(date);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> records = attendanceService.getAllAttendance();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/statistics/{studentId}")
    public ResponseEntity<Map<AttendanceStatus, Long>> getAttendanceStatistics(@PathVariable Long studentId) {
        Map<AttendanceStatus, Long> stats = attendanceService.getAttendanceStatistics(studentId);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/students-by-status")
    public ResponseEntity<List<AttendanceDTO>> getStudentsByAttendanceStatus(
            @RequestParam AttendanceStatus status,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<AttendanceDTO> records = attendanceService.getStudentsByAttendanceStatus(status, date);
        return ResponseEntity.ok(records);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record deleted successfully");
    }
}
