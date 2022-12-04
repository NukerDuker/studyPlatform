package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import ru.skillfactory.studyPlatform.entity.Lesson;
import ru.skillfactory.studyPlatform.entity.Student;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentRateService {

    private final StudentService studentService;


}
