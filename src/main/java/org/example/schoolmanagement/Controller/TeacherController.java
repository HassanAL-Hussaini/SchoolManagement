package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiResponse;
import org.example.schoolmanagement.Model.Teacher;
import org.example.schoolmanagement.Repository.TeacherRepo;
import org.example.schoolmanagement.Service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final TeacherRepo teacherRepo;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTeacher() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
    @PostMapping("/add-teacher")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return ResponseEntity.ok(new ApiResponse("teacher added successfully"));
    }
    @PutMapping("/change/{teacherId}")
    public ResponseEntity<?> changeTeacher(@PathVariable Integer teacherId,@Valid @RequestBody Teacher teacher) {
        teacherService.UpdateTeacher(teacherId,teacher);
        return ResponseEntity.ok(new ApiResponse("teacher updated successfully"));
    }

    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer teacherId) {

        teacherService.DeleteTeacher(teacherId);
        return ResponseEntity.ok(new ApiResponse("teacher deleted successfully"));
    }
    @GetMapping("/get-teacher-by-id/{teacherId}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer teacherId) {
       return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(teacherId));
    }

    // GET: اسم المعلم بحسب رقم الكورس
    @GetMapping("/teacher-name/{courseId}")
    public ResponseEntity<?> getTeacherName(@PathVariable Integer courseId) {
        String teacherName = teacherService.getTeacherNameByCourseID(courseId);
        return ResponseEntity.ok(teacherName);
    }

}
