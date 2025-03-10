package com.sesc.unistudycircle.topic_service.dtos;

import lombok.Data;

@Data
public class StudentInfo {
    private String externalStudentId;
    private String firstName;
    private String lastName;
    private String email;
}
