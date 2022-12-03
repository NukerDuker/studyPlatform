package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.repository.CourseRepo;

import java.util.Map;
import java.util.Optional;

@Service
@Data
public class CourseService {

    private final CourseRepo courseRepo;

    public ResponseEntity<Object> saveCourse(Course newCourse) {
        return this.uniqueCheckAndReturn(newCourse);

    }

    public ResponseEntity<Object> changeCourse(Course changedCourse) {
        Optional<Course> originalCourse = courseRepo.findById(changedCourse.getId());
        if (originalCourse.isPresent()) {
            return this.uniqueCheckAndReturn(changedCourse);
        } else {
            return ResponseEntity.ok().body(Map.of("error", "Course not found"));
        }
    }

    private ResponseEntity<Object> uniqueCheckAndReturn(Course courseToCheck) {
        try {
            Course course = courseRepo.save(courseToCheck);
            return ResponseEntity.ok(course);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.ok(Map.of("error", "Title should be unique"));
        }
    }
}
