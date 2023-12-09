package ru.skillfactory.studyPlatform.models;


import lombok.Data;

@Data
public class StudentAndCourseRequest {

    private long studentId;
    private long courseId;
}
