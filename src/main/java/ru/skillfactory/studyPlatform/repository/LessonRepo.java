package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.Lesson;

public interface LessonRepo extends CrudRepository<Lesson, Long> {
}
