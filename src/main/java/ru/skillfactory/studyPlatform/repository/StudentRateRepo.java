package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.StudentRate;

public interface StudentRateRepo extends CrudRepository<StudentRate, Long> {
}
