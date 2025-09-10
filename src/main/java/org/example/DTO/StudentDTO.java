package org.example.DTO;


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
