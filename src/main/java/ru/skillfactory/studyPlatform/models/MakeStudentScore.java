package ru.skillfactory.studyPlatform.models;

import lombok.Data;

@Data
public class MakeStudentScore {

    private long lessonId;
    private long studentId;
    private double score;
}
