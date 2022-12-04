package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

@Data
public class ChangeStudentName {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
}
