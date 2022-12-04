package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.repository.CourseRepo;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.util.Map;
import java.util.Optional;

@Service
@Data
public class StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    public ResponseEntity<Object> saveStudent(Student newStudent) {
        Student student = studentRepo.save(newStudent);
        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<Object> getStudent(long studentId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    public ResponseEntity<Object> changeStudentName(Student changedStudent) {
        Optional<Student> originalStudent = studentRepo.findById(changedStudent.getId());
        if (originalStudent.isPresent()) {
            Student student = this.updateStudentsName(changedStudent, originalStudent.get());
            studentRepo.save(student);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    public ResponseEntity<Object> changeStudentGroup(long studentId, int groupId) {
        Optional<Student> student = studentRepo.findById(studentId);
        if (student.isPresent()) {
            student.get().setGroupId(groupId);
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }

    public ResponseEntity<Object> addCourse(long studentId, long courseId) {
        Optional<Student> student = studentRepo.findById(studentId);
        Optional<Course> course = courseRepo.findById(courseId);
        if (student.isPresent() && course.isPresent()) {
            student.get().addCourse(course.get());
            studentRepo.save(student.get());
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student or course not found"));
        }
    }
    private Student updateStudentsName(Student changedStudent, Student originalStudent) {
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
