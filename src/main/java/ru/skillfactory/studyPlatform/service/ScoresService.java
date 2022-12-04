package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.entity.Lesson;
import ru.skillfactory.studyPlatform.entity.Score;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.jsonModels.MakeStudentScore;
import ru.skillfactory.studyPlatform.repository.LessonRepo;
import ru.skillfactory.studyPlatform.repository.ScoresRepo;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Data
@Service
public class ScoresService {

    private final ScoresRepo scoresRepo;

    private final StudentRepo studentRepo;

    private final LessonRepo lessonRepo;

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
        Course course = this.findCourse(student.get().getCourses(), request.getLessonId());
        // TODO: 04.12.2022 Check if it is possible below
        if (course == null) return ResponseEntity.ok(Map.of("error", "Course not found"));

        this.calculateStudentRate(course);
        studentRepo.save(student.get());
        return ResponseEntity.ok(student);
    }

    private void calculateStudentRate(Course course) {

    }

    private Course findCourse(Set<Course> courses, long lessonId) {
        Optional<Lesson> lesson = lessonRepo.findById(lessonId);
        Optional<Course> course;
        if (lesson.isPresent()) {
            course = courses.stream()
                    .filter(x -> x.getLessons().contains(lesson.get()))
                    .findFirst();
            System.out.println(course.get());
            return course.get();
        }
        return null;
    }


}
