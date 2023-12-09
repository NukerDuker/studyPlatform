package ru.skillfactory.studyPlatform.service

import lombok.Data
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.skillfactory.studyPlatform.entity.Student
import ru.skillfactory.studyPlatform.jsonModels.ChangeStudentName
import ru.skillfactory.studyPlatform.repository.CourseRepo
import ru.skillfactory.studyPlatform.repository.StudentRepo


@Service
@Data
class StudentService {

    private val studentRepo: StudentRepo? = null
    private val courseRepo: CourseRepo? = null

    /**
     * Method saves student to database.
     * @param newStudent - student object to save.
     * @return Json representation of Student object.
     */
    fun saveStudent(newStudent: Student): ResponseEntity<Any> {
        val student = studentRepo!!.save(newStudent)
        return ResponseEntity.ok().body(student)
    }

    /**
     * Check if student with given Id exists, and return all fields of Student object.
     * @param studentId - student`s id in database.
     * @return Json representation of Student object or error message.
     */
    fun getStudent(studentId: Long): ResponseEntity<Any> {
        val student = studentRepo!!.findById(studentId)
        return if (student.isPresent) {
            ResponseEntity.ok(student)
        } else {
            ResponseEntity.ok(mapOf("error" to "Student not found"))
        }
    }

    /**
     * Change student`s first name, last name or middle name. Accept 2/3/4 parameters.
     * @param changedStudent - required parameter Id and fields to change. Can accept one, two and three parameters to change.
     * @return Json representation of Student object with updated fields or error message.
     */
    fun changeStudentName(changedStudent: ChangeStudentName): ResponseEntity<Any> {
        val originalStudent = studentRepo!!.findById(changedStudent.id)
        if (originalStudent.isPresent) {
            val student = this.updateStudentsName(changedStudent, originalStudent.get())
            studentRepo.save(student)
            return ResponseEntity.ok(student)
        } else {
            return ResponseEntity.ok(mapOf("error" to "Student not found"))
        }
    }

    /**
     * Change student`s groupId.
     * @param studentId - id of student to change group.
     * @param groupId - new id of group to accept.
     * @return Json representation of Student object with updated groupId or error message.
     */
    fun changeStudentGroup(studentId: Long, groupId: Int): ResponseEntity<Any> {
        val student = studentRepo!!.findById(studentId)
        if (student.isPresent) {
            student.get().groupId = groupId
            return ResponseEntity.ok(student.get())
        } else {
            return ResponseEntity.ok(mapOf("error" to "Student not found"))
        }
    }

    /**
     * Add one more link between current student and course. Doesnt effect previous connections.
     * @param studentId - id of student to add course.
     * @param courseId - id of course to add.
     * @return Json representation of Student object with added course or error message.
     */
    fun addCourse(studentId: Long, courseId: Long): ResponseEntity<Any> {
        val student = studentRepo!!.findById(studentId)
        val course = courseRepo!!.findById(courseId)
        if (student.isPresent && course.isPresent) {
            student.get().addCourse(course.get())
            studentRepo.save(student.get())
            return ResponseEntity.ok(student)
        } else {
            return ResponseEntity.ok(mapOf("error" to "Course or student not found"))
        }
    }

    /**
     * Delete link between current student and course.
     * @param studentId
     * @param courseId
     * @return Json representation of Student object with updated courses, or error message.
     */
    fun deleteCourse(studentId: Long, courseId: Long): ResponseEntity<Any> {
        val student = studentRepo!!.findById(studentId)
        val course = courseRepo!!.findById(courseId)
        if (student.isPresent && course.isPresent) {
            student.get().deleteCourse(course.get())
            studentRepo.save(student.get())
            return ResponseEntity.ok(student.get())
        } else {
            return ResponseEntity.ok(mapOf("error" to "Course doesnt belong to current student, or student not found"))
        }
    }

    /**
     * Change student`s current name, last name or middle name to the given string.
     * @param changedStudent - Json with student id and new name, last name or middle name (2/3/4 parameters).
     * @param originalStudent - current student with old values.
     * @return originalStudent with updated fields or error message.
     */
    private fun updateStudentsName(changedStudent: ChangeStudentName, originalStudent: Student): Student {
        if (changedStudent.firstName != null) {
            println(changedStudent.firstName)
            originalStudent.firstName = changedStudent.firstName
        }
        if (changedStudent.lastName != null) {
            originalStudent.lastName = changedStudent.lastName
        }
        if (changedStudent.middleName != null) {
            originalStudent.middleName = changedStudent.middleName
        }
        return originalStudent
    }
}
