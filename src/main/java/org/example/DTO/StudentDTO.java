package org.example.DTO;


import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
}
