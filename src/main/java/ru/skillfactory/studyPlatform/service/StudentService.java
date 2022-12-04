package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.jsonModels.ChangeStudentName;
import ru.skillfactory.studyPlatform.repository.CourseRepo;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.util.Map;
import java.util.Optional;

@Service
@Data
public class StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    /**
     * Method saves student to database.
     * @param newStudent - student object to save.
     * @return Json representation of Student object.
     */
    public ResponseEntity<Object> saveStudent(Student newStudent) {
        Student student = studentRepo.save(newStudent);
        return ResponseEntity.ok().body(student);
    }

    /**
     * Check if student with given Id exists, and return all fields of Student object.
     * @param studentId - student`s id in database.
     * @return Json representation of Student object or error message.
     */
    public ResponseEntity<Object> getStudent(long studentId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    /**
     * Change student`s first name, last name or middle name. Accept 2/3/4 parameters.
     * @param changedStudent - required parameter Id and fields to change. Can accept one, two and three parameters to change.
     * @return Json representation of Student object with updated fields or error message.
     */
    public ResponseEntity<Object> changeStudentName(ChangeStudentName changedStudent) {
        Optional<Student> originalStudent = studentRepo.findById(changedStudent.getId());
        if (originalStudent.isPresent()) {
            Student student = this.updateStudentsName(changedStudent, originalStudent.get());
            studentRepo.save(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    /**
     * Change student`s groupId.
     * @param studentId - id of student to change group.
     * @param groupId - new id of group to accept.
     * @return Json representation of Student object with updated groupId or error message.
     */
    public ResponseEntity<Object> changeStudentGroup(long studentId, int groupId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isPresent()) {
            student.get().setGroupId(groupId);
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    /**
     * Add one more link between current student and course. Doesnt effect previous connections.
     * @param studentId - id of student to add course.
     * @param courseId - id of course to add.
     * @return Json representation of Student object with added course or error message.
     */
    public ResponseEntity<Object> addCourse(long studentId, long courseId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Course> course = courseRepo.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            student.get().addCourse(course.get());
            studentRepo.save(student.get());
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Course doesnt belong to current student, or student not found"));
        }
    }

    /**
     * Delete link between current student and course.
     * @param studentId
     * @param courseId
     * @return Json representation of Student object with updated courses, or error message.
     */
    public ResponseEntity<Object> deleteCourse(long studentId, long courseId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Course> course = courseRepo.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            student.get().deleteCourse(course.get());
            studentRepo.save(student.get());
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.ok(Map.of("error", "Course doesnt belong to current student, or student not found"));
        }
    }

    /**
     * Change student`s current name, last name or middle name to the given string.
     * @param changedStudent - Json with student id and new name, last name or middle name (2/3/4 parameters).
     * @param originalStudent - current student with old values.
     * @return originalStudent with updated fields or error message.
     */
    private Student updateStudentsName(ChangeStudentName changedStudent, Student originalStudent) {
        if (changedStudent.getFirstName() != null) {
            System.out.println(changedStudent.getFirstName());
            originalStudent.setFirstName(changedStudent.getFirstName());
        }
        if (changedStudent.getLastName() != null) {
            originalStudent.setLastName(changedStudent.getLastName());
        }
        if (changedStudent.getMiddleName() != null) {
            originalStudent.setMiddleName(changedStudent.getMiddleName());
        }
        return originalStudent;
    }
}
