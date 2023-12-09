package ru.skillfactory.studyPlatform.service

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.skillfactory.studyPlatform.entity.Course
import ru.skillfactory.studyPlatform.jsonModels.ChangeCourseDates
import ru.skillfactory.studyPlatform.jsonModels.ChangeCourseTitle
import ru.skillfactory.studyPlatform.repository.CourseRepo
import ru.skillfactory.studyPlatform.repository.LessonRepo


@Service
@RequiredArgsConstructor
class CourseService {
    private val courseRepo: CourseRepo? = null
    private val lessonRepo: LessonRepo? = null

    /**
     * Method saves course to database.
     * @param newCourse - course object to save.
     * @return Json representation of Course object.
     */
    fun saveCourse(newCourse: Course): ResponseEntity<Any> {
        return if (this.isUnique(newCourse.title)) {
            ResponseEntity.ok(courseRepo!!.save(newCourse))
        } else {
            ResponseEntity.ok(java.util.Map.of("error", "Title should be unique"))
        }
    }

    /**
     * Check if course with given Id exists, and return all fields of Course object.
     * @param id - course`s id in database.
     * @return Json representation of Course object or error message.
     */
    fun getCourse(id: Long): ResponseEntity<Any> {
        return this.checkIfExistAndReturn(id)
    }

    /**
     * Method checks, if course exist, and return json representation of it`s object, or error message.
     * @param id - id of course to check
     * @return
     */
    private fun checkIfExistAndReturn(id: Long): ResponseEntity<Any> {
        val optCourse = courseRepo!!.findById(id)
        return if (optCourse.isPresent) ResponseEntity.ok(optCourse.get()) else ResponseEntity.ok(
            java.util.Map.of(
                "error",
                "Course not found"
            )
        )
    }

    /**
     * Method rename course, if new title is unique for the table courses_tab.
     * @param idAndNewTitle - id and new title for the course
     * @return
     */
    fun changeCourseTitle(idAndNewTitle: ChangeCourseTitle): ResponseEntity<Any> {
        val response: Map<String, Any>
        val originalCourse = courseRepo!!.findById(idAndNewTitle.courseId)
        response = if (originalCourse.isPresent) {
            if (isUnique(idAndNewTitle.title)) {
                return this.removeOldTitleWithNew(idAndNewTitle, originalCourse.get())
            } else {
                java.util.Map.of<String, Any>("error", "Title should be unique")
            }
        } else {
            java.util.Map.of<String, Any>("error", "Course not found")
        }
        return ResponseEntity.ok(response)
    }

    /**
     * Change start date and end date of the given course. Accept start/end dates together, or separate.
     * @param idAndDates - id, start date (optional), end date (optional).
     * @return Json representation of course object, or error message.
     */
    fun changeCourseDates(idAndDates: ChangeCourseDates): ResponseEntity<Any> {
        val course = courseRepo!!.findById(idAndDates.courseId)
        return if (course.isPresent) {
            if (idAndDates.startDate != null) course.get().startDate = idAndDates.startDate
            else if (idAndDates.endDate != null) course.get().endDate = idAndDates.endDate
            ResponseEntity.ok(ru.skillfactory.studyPlatform.entity.Course)
        } else {
            ResponseEntity.ok(java.util.Map.of("error", "Course not found"))
        }
    }

    fun addLesson(courseId: Long, lessonId: Long): ResponseEntity<Any> {
        val course = courseRepo!!.findById(courseId)
        val lesson = lessonRepo!!.findById(lessonId)
        if (course.isPresent && lesson.isPresent) {
            course.get().addLesson(lesson.get())
            courseRepo.save(course.get())
            return ResponseEntity.ok(course)
        } else {
            return ResponseEntity.ok(java.util.Map.of("error", "Course or lesson not found"))
        }
    }

    private fun removeOldTitleWithNew(
        courseDataToUpdate: ChangeCourseTitle,
        originalCourse: Course
    ): ResponseEntity<Any> {
        if (courseDataToUpdate.title != null) {
            originalCourse.title = courseDataToUpdate.title
        }
        val course = courseRepo!!.save(originalCourse)
        return ResponseEntity.ok(course)
    }

    private fun isUnique(title: String): Boolean {
        val checkCourse = courseRepo!!.findByTitle(title)
        return checkCourse.isEmpty
    }
}
