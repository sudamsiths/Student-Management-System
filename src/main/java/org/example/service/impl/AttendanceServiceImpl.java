package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.DTO.AttendanceDTO;
import org.example.entity.AttendanceEntity;
import org.example.entity.AdminEntity;
import org.example.entity.StudentEntity;
import org.example.repository.AttendanceRepository;
import org.example.repository.AdminRepository;
import org.example.repository.StudentRepository;
import org.example.service.AttendanceService;
import org.example.utill.AttendanceStatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO) {
        AttendanceEntity attendanceEntity = new AttendanceEntity();
        attendanceEntity.setDate(attendanceDTO.getDate());
        attendanceEntity.setStatus(attendanceDTO.getStatus());

        Optional<StudentEntity> studentOpt = studentRepository.findById(attendanceDTO.getStudentId());
        Optional<AdminEntity> adminOpt = adminRepository.findById(attendanceDTO.getAdminId());

        if (studentOpt.isPresent() && adminOpt.isPresent()) {
            attendanceEntity.setStudent(studentOpt.get());
            attendanceEntity.setMarkedBy(adminOpt.get());

            AttendanceEntity saved = attendanceRepository.save(attendanceEntity);
            return mapToDTO(saved);
        }
        throw new RuntimeException("Student or Admin not found");
    }

    @Override
    public List<AttendanceDTO> markBulkAttendance(List<AttendanceDTO> attendanceDTOs) {
        return attendanceDTOs.stream()
                .map(this::markAttendance)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDTO updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        Optional<AttendanceEntity> existingOpt = attendanceRepository.findById(id);
        if (existingOpt.isPresent()) {
            AttendanceEntity existing = existingOpt.get();
            existing.setStatus(attendanceDTO.getStatus());
            existing.setDate(attendanceDTO.getDate());

            AttendanceEntity updated = attendanceRepository.save(existing);
            return mapToDTO(updated);
        }
        throw new RuntimeException("Attendance record not found with id: " + id);
    }

    @Override
    public List<AttendanceDTO> getAttendanceForStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceForStudentByDateRange(Long studentId, LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByStudentIdAndDateBetween(studentId, startDate, endDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<AttendanceStatus, Long> getAttendanceStatistics(Long studentId) {
        List<Object[]> results = attendanceRepository.getAttendanceStatistics(studentId);
        Map<AttendanceStatus, Long> stats = new HashMap<>();

        // Initialize all statuses with 0
        for (AttendanceStatus status : AttendanceStatus.values()) {
            stats.put(status, 0L);
        }

        // Fill in actual counts
        for (Object[] result : results) {
            AttendanceStatus status = (AttendanceStatus) result[0];
            Long count = (Long) result[1];
            stats.put(status, count);
        }

        return stats;
    }

    @Override
    public List<AttendanceDTO> getStudentsByAttendanceStatus(AttendanceStatus status, LocalDate date) {
        return attendanceRepository.findByStatusAndDate(status, date)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Long id) {
        if (attendanceRepository.existsById(id)) {
            attendanceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Attendance record not found with id: " + id);
        }
    }

    private AttendanceDTO mapToDTO(AttendanceEntity entity) {
        AttendanceDTO dto = modelMapper.map(entity, AttendanceDTO.class);
        dto.setStudentId(entity.getStudent().getId());
        dto.setAdminId(entity.getMarkedBy().getId());
        dto.setStudentName(entity.getStudent().getName());
        dto.setAdminName(entity.getMarkedBy().getName());
        return dto;
    }
}
