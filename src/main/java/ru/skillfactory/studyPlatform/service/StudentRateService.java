package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.StudentRate;
import ru.skillfactory.studyPlatform.repository.StudentRateRepo;


@Data
@Service
public class StudentRateService {

    private final StudentRateRepo studentRateRepo;

    public StudentRate saveRate(long courseId, double rate) {
        StudentRate studentRate = StudentRate.builder()
                .courseId(courseId)
                .rate(rate)
                .build();
        return studentRateRepo.save(studentRate);
    }
}
