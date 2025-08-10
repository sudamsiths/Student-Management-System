package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.DTO.CoursesDTO;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "courses")
public class CoursesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String duration;
    private Date startDate;

    public CoursesDTO getDTO(){
        CoursesDTO courseDTO = new CoursesDTO();
        courseDTO.setId(id);
        courseDTO.setName(name);
        courseDTO.setDuration(duration);
        courseDTO.setStartDate(startDate);
        return courseDTO;
    }

}
