package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.Course;

import java.util.Optional;

public interface CourseRepo extends CrudRepository<Course, Long> {
    Optional<Course> findByTitle(String title);
}
