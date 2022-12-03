package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skillfactory.studyPlatform.entity.Student;

public interface StudentRepo extends CrudRepository<Student, Long> {
}
