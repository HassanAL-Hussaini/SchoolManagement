package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiResponse;
import org.example.schoolmanagement.DTO.StudentOutDTO;
import org.example.schoolmanagement.Model.Student;
import org.example.schoolmanagement.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // GET: جميع الطلاب
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    // POST: إضافة طالب
    @PostMapping("/add-student")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(201).body(new ApiResponse("student added successfully"));
    }

    // PUT: تحديث طالب
    @PutMapping("/change/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id,
                                           @Valid @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.ok(new ApiResponse("student updated successfully"));
    }

    // DELETE: حذف طالب
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new ApiResponse("student deleted successfully"));
    }

    // POST: تسجيل الطالب في كورس
    @PostMapping("/assign/{studentId}/{courseId}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Integer studentId,
                                                   @PathVariable Integer courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.ok(new ApiResponse("course assigned to student successfully"));
    }

    // GET: طلاب كورس معيّن على شكل DTO
    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<?> getStudentsByCourseDto(@PathVariable Integer courseId) {
        List<StudentOutDTO> dtos = studentService.getStudentByCourseId(courseId);
        return ResponseEntity.ok(dtos);
    }

    // (اختياري) GET: طلاب كورس معيّن ككيانات خام
    @GetMapping("/by-course-raw/{courseId}")
    public ResponseEntity<?> getStudentsByCourseRaw(@PathVariable Integer courseId) {
        return ResponseEntity.ok(studentService.getStudentsByCourseId(courseId));
    }

    // PUT: تغيير تخصّص الطالب
    @PutMapping("/change-major/{studentId}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer studentId,
                                         @RequestParam String major) {
        studentService.ChangeMajor(studentId, major);
        return ResponseEntity.ok(new ApiResponse("student major changed successfully"));
    }
}
