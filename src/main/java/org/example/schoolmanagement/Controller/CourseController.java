package org.example.schoolmanagement.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolmanagement.API.ApiResponse;
import org.example.schoolmanagement.Model.Course;
import org.example.schoolmanagement.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    // GET: جميع الكورسات
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    // POST: إضافة كورس
    @PostMapping("/add-course")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.ok(new ApiResponse("course added successfully"));
    }

    // PUT: تحديث كورس
    @PutMapping("/change/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer courseId,
                                          @Valid @RequestBody Course course) {
        courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(new ApiResponse("course updated successfully"));
    }

    // DELETE: حذف كورس
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok(new ApiResponse("course deleted successfully"));
    }


    @PutMapping("/{teacherId}/assign/{courseId}")
    public ResponseEntity<?> assignCourseToTeacher(@PathVariable Integer teacherId ,@PathVariable Integer courseId ){
        courseService.assignCourseToTeacher(teacherId,courseId);
        return ResponseEntity.ok(new ApiResponse("course assign to teacher Successfully"));
    }


}
