package org.example.schoolmanagement.Service;

import lombok.AllArgsConstructor;
import org.example.schoolmanagement.API.ApiException;
import org.example.schoolmanagement.Model.Address;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.OutDTO.TeacherOutDTO;
import org.example.schoolmanagement.Repository.AddressRepo;
import org.example.schoolmanagement.Repository.TeacherRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor

public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final AddressRepo addressRepo;

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    public void UpdateTeacher(Integer id , Teacher teacher) {
        Teacher oldTeacher = teacherRepo.findTeacherById(id);
        if(oldTeacher == null) {
            throw new ApiException("Teacher not found");
        }
        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setAddress(teacher.getAddress());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setSalary(teacher.getSalary());
        teacherRepo.save(oldTeacher);
    }

    public void DeleteTeacher(Integer id) {
        Teacher teacher = teacherRepo.findTeacherById(id);
        if(teacher == null) {
            throw new ApiException("Teacher not found");
        }
        teacherRepo.delete(teacher);
    }

    public TeacherOutDTO getTeacherById(Integer teacherId) {
        Teacher teacher = teacherRepo.findTeacherById(teacherId);
        Address address = addressRepo.findAddressById(teacherId);
        if(teacher == null || address == null) {
            throw new ApiException("Teacher/Address not found");
        }
        return new TeacherOutDTO(teacher.getName(),teacher.getAge(),teacher.getEmail()
        ,teacher.getSalary(),address.getArea(), address.getStreet(), address.getBuildingNumber());
    }
}
