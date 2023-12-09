package ru.skillfactory.studyPlatform.models;

import lombok.Data;

@Data
public class ChangeStudentName {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
}
