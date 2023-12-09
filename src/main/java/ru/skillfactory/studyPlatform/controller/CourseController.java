package ru.skillfactory.studyPlatform.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.models.ChangeCourseDates;
import ru.skillfactory.studyPlatform.models.ChangeCourseTitle;
import ru.skillfactory.studyPlatform.models.CourseAndLessonRequest;
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
    @Operation(summary = "Required field - courseId, title.")
    @PostMapping("/change/title")
    public ResponseEntity<Object> changeCourseTitle(@RequestBody ChangeCourseTitle idAndNewTitle) {
        return courseService.changeCourseTitle(idAndNewTitle);
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<Object> getCourse(@PathVariable long courseId) {
        return courseService.getCourse(courseId);
    }

    @Operation(summary = "Required field - id, start and end dates are optional. Pass both of them, or one of them.")
    @PostMapping("/change/date")
    public ResponseEntity<Object> changeCourseDates(@RequestBody ChangeCourseDates idAndDates) {
        return courseService.changeCourseDates(idAndDates);
    }
    @Operation(summary = "One request - one lesson!")
    @PutMapping("/add/lesson")
    public ResponseEntity<Object> addLesson(@RequestBody CourseAndLessonRequest request) {
        return courseService.addLesson(request.getCourseId(), request.getLessonId());
    }

}
