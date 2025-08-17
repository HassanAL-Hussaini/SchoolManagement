package org.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @NotEmpty
    @Column(columnDefinition = "varchar(30) not null unique")
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id" , referencedColumnName = "id")
    @JsonIgnore
    private Teacher teacher;

    @ManyToMany
    private Set<Student> students;
}
