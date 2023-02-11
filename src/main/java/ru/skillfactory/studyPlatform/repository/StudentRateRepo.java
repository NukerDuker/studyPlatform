package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.studyPlatform.entity.StudentRate;

@Repository
public interface StudentRateRepo extends CrudRepository<StudentRate, Long> {
}
