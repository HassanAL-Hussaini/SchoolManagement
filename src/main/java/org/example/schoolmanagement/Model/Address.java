package org.example.schoolmanagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Address {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    private String area;

    @Column(columnDefinition = "varchar(30) not null")
    private String buildingNumber;

    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}
