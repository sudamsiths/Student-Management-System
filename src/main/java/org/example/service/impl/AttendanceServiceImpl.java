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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
        studentOpt.ifPresent(attendanceEntity::setStudent);
        adminOpt.ifPresent(attendanceEntity::setMarkedBy);

        AttendanceEntity saved = attendanceRepository.save(attendanceEntity);
        return modelMapper.map(saved, AttendanceDTO.class);
    }

    @Override
    public List<AttendanceDTO> getAttendanceForStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(a -> modelMapper.map(a, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceDTO> getAllAttendance() {
        return attendanceRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a, AttendanceDTO.class))
                .collect(Collectors.toList());
    }
}

