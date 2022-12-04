package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Lesson;
import ru.skillfactory.studyPlatform.repository.LessonRepo;

import java.util.Map;
import java.util.Optional;

@Data
@Service
public class LessonService {

    private final LessonRepo lessonRepo;

    public ResponseEntity<Object> saveLesson(Lesson lesson){
        return ResponseEntity.ok(lessonRepo.save(lesson));
    }

    public ResponseEntity<Object> getLesson(long lessonId){
        Optional<Lesson> lesson = lessonRepo.findById(lessonId);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.ok(Map.of("error", "Lesson not found"));
        }
    }

}
