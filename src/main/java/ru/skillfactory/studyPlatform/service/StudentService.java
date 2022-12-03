package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.repository.StudentRepo;

import java.util.Map;
import java.util.Optional;

@Service
@Data
public class StudentService {

    private final StudentRepo studentRepo;

    public ResponseEntity<Object> saveStudent(Student newStudent) {
        Student student = studentRepo.save(newStudent);
        return ResponseEntity.ok().body(student);
    }

    public ResponseEntity<Object> changeStudent(Student changedStudent) {
        Optional<Student> originalStudent = studentRepo.findById(changedStudent.getId());
        if (originalStudent.isPresent()) {
            Student student = studentRepo.save(changedStudent);
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.ok(Map.of("error", "Student not found"));
        }
    }
}
