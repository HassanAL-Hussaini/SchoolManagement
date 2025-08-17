package org.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(columnDefinition ="varchar(30) not null")
    private String name;

    @NotNull
    @Column(columnDefinition = "int not null")
    @Min(17)
    private Integer age;

    @NotEmpty
    @Column(length = 50 , nullable = false)
    private String major;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private Set<Course> courses;
}
