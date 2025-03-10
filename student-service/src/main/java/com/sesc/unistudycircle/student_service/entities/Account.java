package com.sesc.unistudycircle.student_service.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Account {
    private long id;
    private String studentId;
    private boolean hasOutstandingBalance;
}
