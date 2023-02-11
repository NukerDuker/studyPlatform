package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.studyPlatform.entity.Lesson;

@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {
}
