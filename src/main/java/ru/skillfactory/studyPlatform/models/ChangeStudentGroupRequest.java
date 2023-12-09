package ru.skillfactory.studyPlatform.models;

import lombok.Data;

@Data
public class ChangeStudentGroupRequest {

    private long studentId;
    private int groupId;
}
