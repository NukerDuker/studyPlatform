package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

@Data
public class ChangeCourseTitle {

    private long courseId;
    private String title;
}
