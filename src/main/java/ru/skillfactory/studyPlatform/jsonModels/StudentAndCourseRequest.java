package ru.skillfactory.studyPlatform.jsonModels;


import lombok.Data;

@Data
public class StudentAndCourseRequest {

    private long studentId;
    private long courseId;
}
