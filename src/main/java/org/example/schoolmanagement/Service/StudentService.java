package org.example.schoolmanagement.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiException;
import org.example.schoolmanagement.DTO.StudentOutDTO;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Repository.CourseRepo;
import org.example.schoolmanagement.Repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public void addStudent(Student student){
        studentRepo.save(student);
    }

    public void updateStudent(Integer id, Student student){
        Student oldStudent = studentRepo.findStudentById(id);
        if (oldStudent == null)
            throw new ApiException("Student not found");
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepo.save(oldStudent);
    }
//TODO roll Back to it
    public void deleteStudent(Integer studentId){
        Student student = studentRepo.findStudentById(studentId);
        if (student == null)
            throw new ApiException("Student not found");

        // فك الربط من جهة المالك (Course.students) ثم حفظ الـ course

        studentRepo.delete(student);
    }

    public void assignStudentToCourse(Integer studentId , Integer courseId){
        Course course = courseRepo.findCourseById(courseId);
        Student student = studentRepo.findStudentById(studentId);

        if(course == null || student == null){
            throw new ApiException("student or Course Not found");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepo.save(student);
        courseRepo.save(course);
    }

    public List<StudentOutDTO> getStudentByCourseId(Integer courseId){
        Course course = courseRepo.findCourseById(courseId);
        if(course == null ){
            throw new ApiException("course not found");
        }
        if(course.getStudents().isEmpty()){
            throw new ApiException("course Dose not Have student Yet");
        }

        ArrayList<StudentOutDTO> studentOutDTOS = new ArrayList<>() ;

        for (Student student : course.getStudents() ){
            studentOutDTOS.add(new StudentOutDTO(student.getName(),student.getAge(),student.getMajor()));
        }

        return studentOutDTOS;
    }
    //Another Way
    public Set<Student> getStudentsByCourseId(Integer courseId){
        Course course = courseRepo.findCourseById(courseId);
        if(course == null ){
            throw new ApiException("course not found");
        }
        return course.getStudents();
    }

    public void ChangeMajor(Integer studentId , String major){
        Student student = studentRepo.findStudentById(studentId);
        if(studentId == null ){
            throw new ApiException("student Not found");
        }
        Set<Course> courseToRemove = student.getCourses();
        //review one more Time
        for (Course course : courseToRemove){
            // Remove student from course's student list
            course.getStudents().remove(student);
            // Remove course from student's course list
            student.getCourses().remove(course);
            // Save the course to persist the change
            courseRepo.save(course);
        }
        student.setMajor(major);
        studentRepo.save(student);
    }


}
