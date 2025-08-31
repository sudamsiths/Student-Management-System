package org.example.DTO;

import lombok.*;
import org.example.utill.AttendanceStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceDTO {
    private Long id;
    private Date date;
    private AttendanceStatus status;
    private Long studentId;
    private Long adminId;
}
