package org.example.service;

import org.example.DTO.AttendanceDTO;
import java.util.List;

public interface AttendanceService {
    AttendanceDTO markAttendance(AttendanceDTO attendanceDTO);
    List<AttendanceDTO> getAttendanceForStudent(Long studentId);
    List<AttendanceDTO> getAllAttendance();
}

