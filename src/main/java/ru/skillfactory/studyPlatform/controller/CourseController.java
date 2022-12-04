package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.service.CourseService;

@RestController
@Data
@RequestMapping("/")
public class CourseController {

    private final CourseService courseService;
    @PostMapping("/save/course")
    public ResponseEntity<Object> saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/change/course")
    public ResponseEntity<Object> changeCourse(@RequestBody Course course) {
        return courseService.changeCourse(course);
    }


}
