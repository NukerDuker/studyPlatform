package ru.skillfactory.studyPlatform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.studyPlatform.entity.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
}
