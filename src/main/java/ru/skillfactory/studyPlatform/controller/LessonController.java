package ru.skillfactory.studyPlatform.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.studyPlatform.entity.Lesson;
import ru.skillfactory.studyPlatform.service.LessonService;

@RestController
@Data
@RequestMapping("/api/v1/lesson")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/get/{lessonId}")
    public ResponseEntity<Object> getLesson(@PathVariable long lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveLesson(@RequestBody Lesson lesson) {
        return lessonService.saveLesson(lesson);
    }
}
