package org.example.schoolmanagement.Repository;

import org.example.schoolmanagement.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course,Integer> {
    Course findCourseById(Integer id);
}
