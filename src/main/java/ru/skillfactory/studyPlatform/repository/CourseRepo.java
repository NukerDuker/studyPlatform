package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.Course;

public interface CourseRepo extends CrudRepository<Course, Long> {
}
