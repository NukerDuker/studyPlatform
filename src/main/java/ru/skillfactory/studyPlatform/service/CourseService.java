package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.repository.CourseRepo;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;

    public ResponseEntity<Object> saveCourse(Course newCourse) {
        return this.uniqueCheckAndReturn(newCourse);

    }

    public ResponseEntity<Object> getCourse(long id) {
        return this.checkIfExistAndReturn(id);
    }

    private ResponseEntity<Object> checkIfExistAndReturn(long id) {
        Optional<Course> optCourse = courseRepo.findById(id);
        if (optCourse.isPresent()) {
            return ResponseEntity.ok(optCourse.get());
        } else {
            return ResponseEntity.ok(Map.of("error", "Course not found"));
        }
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
