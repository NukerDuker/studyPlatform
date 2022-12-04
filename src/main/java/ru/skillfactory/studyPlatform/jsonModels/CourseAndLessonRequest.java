package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

@Data
public class CourseAndLessonRequest {

    private long courseId;
    private long lessonId;
}
