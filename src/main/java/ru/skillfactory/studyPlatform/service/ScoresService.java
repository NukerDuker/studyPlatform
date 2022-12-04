package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Score;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.jsonModels.MakeStudentScore;
import ru.skillfactory.studyPlatform.repository.ScoresRepo;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.util.Map;
import java.util.Optional;

@Data
@Service
public class ScoresService {

    private final ScoresRepo scoresRepo;

    private final StudentRepo studentRepo;

    /**
     * Method to make score for lesson to student
     * @param request - json representation of Score object.
     * @return json representation of Score object or error message.
     */
    public ResponseEntity<Object> makeScore(MakeStudentScore request) {
        Optional<Student> student = studentRepo.findById(request.getStudentId());
        if (student.isEmpty()) return ResponseEntity.ok(Map.of("error", "Student not found"));
        Score score = Score.builder()
                .lessonId(request.getLessonId())
                .score(request.getScore())
                .build();
        scoresRepo.save(score);
        student.get().addScore(score);
        studentRepo.save(student.get());
        return ResponseEntity.ok(student);
    }

    public ResponseEntity<Object> setStudent(long studentId, long scoreId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Score> score = scoresRepo.findById(scoreId);
        if (student.isPresent() && score.isPresent()) {
            student.get().addScore(score.get());
            studentRepo.save(student.get());
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Score or student not found"));
        }
    }


}
