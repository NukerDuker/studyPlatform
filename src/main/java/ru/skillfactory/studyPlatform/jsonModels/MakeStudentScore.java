package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

@Data
public class MakeStudentScore {

    private long lessonId;
    private long studentId;
    private double score;
}
