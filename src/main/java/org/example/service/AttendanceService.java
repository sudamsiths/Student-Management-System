package org.example.service;

import org.example.DTO.AttendanceDTO;
import org.example.utill.AttendanceStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceService {
    AttendanceDTO markAttendance(AttendanceDTO attendanceDTO);
    List<AttendanceDTO> markBulkAttendance(List<AttendanceDTO> attendanceDTOs);
    AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO);
    List<AttendanceDTO> getAttendanceForStudent(Long studentId);
    List<AttendanceDTO> getAttendanceForStudentByDateRange(Long studentId, LocalDate startDate, LocalDate endDate);
    List<AttendanceDTO> getAttendanceByDate(LocalDate date);
    List<AttendanceDTO> getAllAttendance();
    Map<AttendanceStatus, Long> getAttendanceStatistics(Long studentId);
    List<AttendanceDTO> getStudentsByAttendanceStatus(AttendanceStatus status, LocalDate date);
    void deleteAttendance(Long id);
}
