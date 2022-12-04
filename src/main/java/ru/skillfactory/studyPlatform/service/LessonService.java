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

    /**
     * Method saves lesson to database.
     * @param lesson - lesson object to save.
     * @return Json representation of Lesson object.
     */
    public ResponseEntity<Object> saveLesson(Lesson lesson){
        return ResponseEntity.ok(lessonRepo.save(lesson));
    }

    /**
     * Check if lesson with given Id exists, and return all fields of Lesson object.
     * @param lessonId - lesson`s id in database.
     * @return Json representation of Lesson object or error message.
     */
    public ResponseEntity<Object> getLesson(long lessonId){
        Optional<Lesson> lesson = lessonRepo.findById(lessonId);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.ok(Map.of("error", "Lesson not found"));
        }
    }

}
