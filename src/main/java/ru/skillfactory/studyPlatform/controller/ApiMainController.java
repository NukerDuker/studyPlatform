package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.service.CourseService;
import ru.skillfactory.studyPlatform.service.StudentService;

@RestController
@Data
@RequestMapping("/")
public class ApiMainController {

    private final StudentService studentService;

    private final CourseService courseService;

    @PostMapping("/save/student")
    public ResponseEntity<Object> saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/change/student")
    public ResponseEntity<Object> changeStudent(@RequestBody Student student) {
        return studentService.changeStudentName(student);
    }

    @PostMapping("/save/course")
    public ResponseEntity<Object> saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/change/course")
    public ResponseEntity<Object> changeCourse(@RequestBody Course course) {
        return courseService.changeCourse(course);
    }


}
