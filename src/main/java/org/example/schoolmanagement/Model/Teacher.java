package org.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 50)
    @NotEmpty
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @Min(20)
    @Max(65)
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;

    @Email
    @NotEmpty
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @Positive
    @DecimalMin(value = "14000.0", message = "Salary must be >= 14000")
    @Column(columnDefinition = "int not null")
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "teacher")
    private Set<Course> courses;
}
