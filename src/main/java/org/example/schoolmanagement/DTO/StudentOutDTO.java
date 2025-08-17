package org.example.schoolmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentOutDTO {
    private String name;
    private Integer age;
    private String major;
}
