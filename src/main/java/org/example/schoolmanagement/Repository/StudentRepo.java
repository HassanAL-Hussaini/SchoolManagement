package org.example.schoolmanagement.Repository;

import org.example.schoolmanagement.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    Student findStudentById(Integer id);

}
