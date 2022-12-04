package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.jsonModels.ChangeCourseTitle;
import ru.skillfactory.studyPlatform.service.CourseService;

@RestController
@Data
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;
    @PostMapping("/save")
    public ResponseEntity<Object> saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/change/title")
    public ResponseEntity<Object> changeCourseTitle(@RequestBody ChangeCourseTitle idAndNewTitle) {
        return courseService.changeCourseTitle(idAndNewTitle);
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<Object> getCourse(@PathVariable long courseId) {
        return courseService.getCourse(courseId);
    }


}
