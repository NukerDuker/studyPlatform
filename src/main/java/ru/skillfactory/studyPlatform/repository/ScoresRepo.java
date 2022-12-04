package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.Score;

public interface ScoresRepo extends CrudRepository<Score, Long> {

}
