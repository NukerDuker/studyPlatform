package ru.skillfactory.studyPlatform.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.skillfactory.studyPlatform.entity.Student;
import ru.skillfactory.studyPlatform.entity.StudentRate;
import ru.skillfactory.studyPlatform.repository.StudentRateRepo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;


@Data
@Service
public class StudentRateService {

    private final StudentRateRepo studentRateRepo;

    public StudentRate saveRate(long courseId, double rate, Student student) {
        StudentRate studentRate;
        Optional<StudentRate> rateForCourse = student.getRates().stream().filter(x -> x.getCourseId() == courseId).findFirst();
        double formattedRate = this.convertDouble(rate);
        if (rateForCourse.isPresent()) {
            rateForCourse.get().setRate(formattedRate);
            return studentRateRepo.save(rateForCourse.get());
        } else {
            studentRate = StudentRate.builder()
                    .courseId(courseId)
                    .rate(formattedRate)
                    .build();

        }
        return studentRateRepo.save(studentRate);
    }

    private Double convertDouble(double rate) {
        BigDecimal bd = new BigDecimal(Double.toString(rate));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }
}
