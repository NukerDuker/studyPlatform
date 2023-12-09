package ru.skillfactory.studyPlatform.service

import lombok.Data
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.skillfactory.studyPlatform.entity.Lesson
import ru.skillfactory.studyPlatform.repository.LessonRepo


@Data
@Service
class LessonService {
    private val lessonRepo: LessonRepo? = null

    /**
     * Method saves lesson to database.
     * @param lesson - lesson object to save.
     * @return Json representation of Lesson object.
     */
    fun saveLesson(lesson: Lesson): ResponseEntity<Any> {
        return ResponseEntity.ok(lessonRepo!!.save(lesson))
    }

    /**
     * Check if lesson with given Id exists, and return all fields of Lesson object.
     * @param lessonId - lesson`s id in database.
     * @return Json representation of Lesson object or error message.
     */
    fun getLesson(lessonId: Long): ResponseEntity<Any> {
        val lesson = lessonRepo!!.findById(lessonId)
        return if (lesson.isPresent) {
            ResponseEntity.ok(lesson)
        } else {
            ResponseEntity.ok(mapOf("error" to "Lesson not found"))
        }
    }
}
