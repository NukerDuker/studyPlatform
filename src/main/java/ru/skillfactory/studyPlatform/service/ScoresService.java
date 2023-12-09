package ru.skillfactory.studyPlatform.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.*;
import ru.skillfactory.studyPlatform.models.MakeStudentScore;
import ru.skillfactory.studyPlatform.repository.LessonRepo;
import ru.skillfactory.studyPlatform.repository.ScoresRepo;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Data
@Service
public class ScoresService {

    private final ScoresRepo scoresRepo;

    private final StudentRepo studentRepo;

    private final LessonRepo lessonRepo;

    private final StudentRateService studentRateService;

    /**
     * Method to make score for lesson to student
     * @param request - json representation of Score object.
     * @return json representation of Score object or error message.
     */
    public ResponseEntity<Object> makeScore(MakeStudentScore request) {
        Optional<Student> optStudent = studentRepo.findById(request.getStudentId());
        if (optStudent.isEmpty()) return ResponseEntity.ok(Map.of("error", "Student not found"));

        Course course = this.findCourseByLesson(optStudent.get().getCourses(), request.getLessonId());
        if (course == null) {
            return ResponseEntity.ok(Map.of("error", "Course not found"));
        }

        Student student = this.saveScoreAndAddToStudent(request, optStudent.get());

        Map<Long, Double> lessonIdsAndMaxScores = this.getLessonsBeforeToday(course);
        double rate = this.countRate(student.getScores(), lessonIdsAndMaxScores);
        StudentRate studentRate = studentRateService.saveRate(course.getId(), rate, student);
        student.addRate(studentRate);
        studentRepo.save(student);
        return ResponseEntity.ok(optStudent);
    }

    private double countRate(Set<Score> scores, Map<Long, Double> lessonIdsAndMaxScores) {
        double scoreSum = scores.stream()
                .filter(x -> lessonIdsAndMaxScores.containsKey(x.getLessonId()))
                .mapToDouble(Score::getScore)
                .sum();
        double sumMaxScores = 0;
        for (Double value : lessonIdsAndMaxScores.values()) {
            sumMaxScores += value;
        }
        return scoreSum / sumMaxScores;
    }

    private Student saveScoreAndAddToStudent(MakeStudentScore request, Student student) {
        Score score = Score.builder()
                .lessonId(request.getLessonId())
                .score(request.getScore())
                .build();
        scoresRepo.save(score);
        student.addScore(score);
        return student;
    }

    private Map<Long, Double> getLessonsBeforeToday(Course course) {
        Set<Lesson> lessons = course.getLessons();

        return lessons.stream()
                .filter(x -> x.getDateTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toMap(Lesson::getId, Lesson::getMaxScore));
    }

    private Course findCourseByLesson(Set<Course> courses, long lessonId) {
        Optional<Lesson> lesson = lessonRepo.findById(lessonId);
        Optional<Course> course;
        if (lesson.isPresent()) {
            course = courses.stream()
                    .filter(x -> x.getLessons().contains(lesson.get()))
                    .findFirst();

            return course.get();
        }
        return null;
    }


}
