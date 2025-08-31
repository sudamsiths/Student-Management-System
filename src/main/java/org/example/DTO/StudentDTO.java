package org.example.DTO;


import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<Long> courseIds;
}
