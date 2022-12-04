package ru.skillfactory.studyPlatform.jsonModels;

import lombok.Data;

@Data
public class ChangeStudentGroupRequest {

    private long studentId;
    private int groupId;
}
