package com.sesc.unistudycircle.student_service.entities;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDate;

@Data
public class Invoice {
    @Id
    private Long id;
    private String reference;
    private double amount;
    private LocalDate dueDate;
    private Type type;
    private Status status;
    private Account account;


    public enum Type {
        LIBRARY_FINE,
        TUITION_FEES
    }

    enum Status {
        OUTSTANDING,
        PAID,
        CANCELLED,
    }
}
