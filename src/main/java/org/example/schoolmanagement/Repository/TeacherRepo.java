package org.example.schoolmanagement.Repository;

import org.example.schoolmanagement.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherById(Integer id);
}
