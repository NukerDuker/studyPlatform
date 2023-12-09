package ru.skillfactory.studyPlatform.models;

import lombok.Data;

@Data
public class ChangeCourseTitle {

    private long courseId;
    private String title;
}
