package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiException;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.CourseRepo;
import org.example.schoolmanagement.Repository.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepo courseRepo;
    private final TeacherRepo teacherRepo;

    public List<Course> getCourses(){
        return courseRepo.findAll();
    }
    //I am using the first Flow witch is Marsol Flow😊
    public void addCourse(Course course){
        courseRepo.save(course);
    }

    public void updateCourse(Integer courseId,Course course){
        Course oldCourse = courseRepo.findCourseById(courseId);
        if(oldCourse == null){
            throw new ApiException("course not found");
        }
        oldCourse.setName(course.getName());
        courseRepo.save(oldCourse);
    }

    //this is the First Follow witch is Assigned Manually
    public void assignCourseToTeacher(Integer teacherId , Integer courseId){
        Teacher teacher = teacherRepo.findTeacherById(teacherId);
        Course course = courseRepo.findCourseById(courseId);

        if(teacher == null || course == null){
            throw new ApiException("there is no Teacher/course with this ID");
        }
        course.setTeacher(teacher);
        courseRepo.save(course);
    }

    public void deleteCourse(Integer courseId){
        Course course = courseRepo.findCourseById(courseId);
        if(course == null){
            throw new ApiException("course Not Found");
        }
        courseRepo.delete(course);
    }



}
