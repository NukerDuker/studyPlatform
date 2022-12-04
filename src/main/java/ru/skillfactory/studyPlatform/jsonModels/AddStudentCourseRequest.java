package ru.skillfactory.studyPlatform.jsonModels;


import lombok.Data;

@Data
public class AddStudentCourseRequest {

    private long studentId;
    private long courseId;
}
