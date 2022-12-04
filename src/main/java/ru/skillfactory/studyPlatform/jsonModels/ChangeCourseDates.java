package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeCourseDates {

    private long courseId;
    private LocalDate startDate;
    private LocalDate endDate;
}
