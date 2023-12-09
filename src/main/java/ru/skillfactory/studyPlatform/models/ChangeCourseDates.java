package ru.skillfactory.studyPlatform.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChangeCourseDates {

    private long courseId;
    private LocalDate startDate;
    private LocalDate endDate;
}
