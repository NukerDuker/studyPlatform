package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.studyPlatform.entity.Score;

@Repository
public interface ScoresRepo extends CrudRepository<Score, Long> {

}
