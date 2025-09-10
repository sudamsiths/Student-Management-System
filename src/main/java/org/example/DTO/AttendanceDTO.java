package org.example.DTO;

import lombok.*;
import org.example.utill.AttendanceStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceDTO {
    private Long id;
    private LocalDate date;
    private AttendanceStatus status;
    private Long studentId;
    private Long adminId;
    private String studentName;
    private String adminName;
}
