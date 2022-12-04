package ru.skillfactory.studyPlatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Course;
import ru.skillfactory.studyPlatform.jsonModels.ChangeCourseDates;
import ru.skillfactory.studyPlatform.jsonModels.ChangeCourseTitle;
import ru.skillfactory.studyPlatform.repository.CourseRepo;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;

    /**
     * Method saves course to database.
     * @param newCourse - course object to save.
     * @return Json representation of Course object.
     */
    public ResponseEntity<Object> saveCourse(Course newCourse) {
        if (this.isUnique(newCourse.getTitle())) {
            return ResponseEntity.ok(courseRepo.save(newCourse));
        } else {
            return ResponseEntity.ok(Map.of("error", "Title should be unique"));
        }
    }

    /**
     * Check if course with given Id exists, and return all fields of Course object.
     * @param id - course`s id in database.
     * @return Json representation of Course object or error message.
     */
    public ResponseEntity<Object> getCourse(long id) {
        return this.checkIfExistAndReturn(id);
    }

    /**
     * Method checks, if course exist, and return json representation of it`s object, or error message.
     * @param id - id of course to check
     * @return
     */
    private ResponseEntity<Object> checkIfExistAndReturn(long id) {
        Optional<Course> optCourse = courseRepo.findById(id);
        return optCourse.isPresent() ? ResponseEntity.ok(optCourse.get()) : ResponseEntity.ok(Map.of("error", "Course not found"));
    }

    /**
     * Method rename course, if new title is unique for the table courses_tab.
     * @param idAndNewTitle - id and new title for the course
     * @return
     */
    public ResponseEntity<Object> changeCourseTitle(ChangeCourseTitle idAndNewTitle) {
        Map<String, Object> response;
        Optional<Course> originalCourse = courseRepo.findById(idAndNewTitle.getCourseId());
        if (originalCourse.isPresent()) {
            if (isUnique(idAndNewTitle.getTitle())) {
                return this.removeOldTitleWithNew(idAndNewTitle, originalCourse.get());
            } else {
                response = Map.of("error", "Title should be unique");
            }
        } else {
            response = Map.of("error", "Course not found");
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> changeCourseDates(ChangeCourseDates idAndDates) {
        Optional<Course> course = courseRepo.findById(idAndDates.getCourseId());
        if (course.isPresent()) {
            if (idAndDates.getStartDate() != null) course.get().setStartDate(idAndDates.getStartDate());
            else if (idAndDates.getEndDate() != null) course.get().setEndDate(idAndDates.getEndDate());
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.ok(Map.of("error", "Course not found"));
        }
    }

    private ResponseEntity<Object> removeOldTitleWithNew(ChangeCourseTitle courseDataToUpdate, Course originalCourse) {

            if (courseDataToUpdate.getTitle() != null) {
                originalCourse.setTitle(courseDataToUpdate.getTitle());
            }
            Course course = courseRepo.save(originalCourse);
            return ResponseEntity.ok(course);
    }

    private boolean isUnique(String title) {
        Optional<Course> checkCourse = courseRepo.findByTitle(title);
        return checkCourse.isEmpty();
    }
}
