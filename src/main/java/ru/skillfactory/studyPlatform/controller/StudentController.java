package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.models.StudentAndCourseRequest;
import ru.skillfactory.studyPlatform.models.ChangeStudentGroupRequest;
import ru.skillfactory.studyPlatform.models.ChangeStudentName;
import ru.skillfactory.studyPlatform.service.CourseService;
import ru.skillfactory.studyPlatform.service.StudentService;

@RestController
@Data
@RequestMapping(path = "/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    private final CourseService courseService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getStudent(@PathVariable long id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/change/name")
    public ResponseEntity<Object> changeStudentName(@RequestBody ChangeStudentName request) {
        return studentService.changeStudentName(request);
    }

    @PostMapping("/change/group")
    public ResponseEntity<Object> changeStudentGroup(@RequestBody ChangeStudentGroupRequest request) {
        return studentService.changeStudentGroup(request.getStudentId(), request.getGroupId());
    }

    @PutMapping("/add/course")
    public ResponseEntity<Object> addCourse(@RequestBody StudentAndCourseRequest request) {
        return studentService.addCourse(request.getStudentId(), request.getCourseId());
    }

    @DeleteMapping("/delete/course")
    public ResponseEntity<Object> deleteCourse(@RequestBody StudentAndCourseRequest request) {
        return studentService.deleteCourse(request.getStudentId(), request.getCourseId());
    }
}
