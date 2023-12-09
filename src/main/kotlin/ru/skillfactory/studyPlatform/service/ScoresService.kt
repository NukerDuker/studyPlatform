package ru.skillfactory.studyPlatform.service

import jakarta.transaction.Transactional
import lombok.Data
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.skillfactory.studyPlatform.entity.Course
import ru.skillfactory.studyPlatform.entity.Lesson
import ru.skillfactory.studyPlatform.entity.Score
import ru.skillfactory.studyPlatform.entity.Student
import ru.skillfactory.studyPlatform.jsonModels.MakeStudentScore
import ru.skillfactory.studyPlatform.repository.LessonRepo
import ru.skillfactory.studyPlatform.repository.ScoresRepo
import ru.skillfactory.studyPlatform.repository.StudentRepo
import java.time.LocalDateTime
import java.util.*
import java.util.stream.Collectors


@Transactional
@Data
@Service
class ScoresService {
    private val scoresRepo: ScoresRepo? = null

    private val studentRepo: StudentRepo? = null

    private val lessonRepo: LessonRepo? = null

    private val studentRateService: StudentRateService? = null

    /**
     * Method to make score for lesson to student
     * @param request - json representation of Score object.
     * @return json representation of Score object or error message.
     */
    fun makeScore(request: MakeStudentScore): ResponseEntity<Any> {
        val optStudent = studentRepo!!.findById(request.studentId)
        if (optStudent.isEmpty) return ResponseEntity.ok(java.util.Map.of("error", "Student not found"))

        val course = this.findCourseByLesson(optStudent.get().courses, request.lessonId)
            ?: return ResponseEntity.ok(java.util.Map.of("error", "Course not found"))

        val student = this.saveScoreAndAddToStudent(request, optStudent.get())

        val lessonIdsAndMaxScores = this.getLessonsBeforeToday(course)
        val rate = this.countRate(student.scores, lessonIdsAndMaxScores)
        println("Student rate is : $rate")
        val studentRate = studentRateService!!.saveRate(course.id, rate, student)
        student.addRate(studentRate)
        studentRepo.save(student)
        return ResponseEntity.ok(optStudent)
    }

    private fun countRate(scores: Set<Score>, lessonIdsAndMaxScores: Map<Long, Double>): Double {
        val scoreSum = scores.stream()
            .filter { x: Score -> lessonIdsAndMaxScores.containsKey(x.lessonId) }
            .mapToDouble { obj: Score -> obj.score }
            .sum()
        var sumMaxScores = 0.0
        for (value in lessonIdsAndMaxScores.values) {
            sumMaxScores += value
        }
        return scoreSum / sumMaxScores
    }

    private fun saveScoreAndAddToStudent(request: MakeStudentScore, student: Student): Student {
        val score = Score.builder()
            .lessonId(request.lessonId)
            .score(request.score)
            .build()
        scoresRepo!!.save(score)
        student.addScore(score)
        return student
    }

    private fun getLessonsBeforeToday(course: Course): Map<Long, Double> {
        val lessons = course.lessons

        return lessons.stream()
            .filter { x: Lesson -> x.dateTime.isBefore(LocalDateTime.now()) }
            .collect(
                Collectors.toMap(
                    { obj: Lesson -> obj.id },
                    { obj: Lesson -> obj.maxScore })
            )
    }

    private fun findCourseByLesson(courses: Set<Course>, lessonId: Long): Course? {
        val lesson = lessonRepo!!.findById(lessonId)
        val course: Optional<Course>
        if (lesson.isPresent) {
            course = courses.stream()
                .filter { x: Course -> x.lessons.contains(lesson.get()) }
                .findFirst()
            println(course.get())
            return course.get()
        }
        return null
    }
}
