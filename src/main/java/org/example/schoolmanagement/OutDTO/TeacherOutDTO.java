package org.example.schoolmanagement.OutDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherOutDTO {
    private String teacher_name;
    private Integer teacher_age;
    private String email;
    private Double salary;
    private String area;
    private String street;
    private String buildingNumber;

}
