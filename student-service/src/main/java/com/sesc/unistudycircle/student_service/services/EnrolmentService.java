package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EnrolmentService {
    private final IntegrationService integrationService;

    public EnrolmentService(IntegrationService integrationService)
    {
        this.integrationService = integrationService;
    }

    public void enrollStudent(Student student, Course course) {
        try{

            if(integrationService.getStudentAccount(student.getStudentId()) != null) {

                Account account = integrationService.getStudentAccount(student.getStudentId());
                System.out.println("Student Account Exists, giving them another invoice");
                Invoice invoice = new Invoice();
                invoice.setAccount(account);
                invoice.setType(Invoice.Type.TUITION_FEES);
                invoice.setAmount(course.getFee());
                invoice.setDueDate(LocalDate.now());

                integrationService.createCourseFeeInvoice(invoice);
            }
            else {
                Account account = new Account();
                account.setStudentId(student.getStudentId());
                account.setHasOutstandingBalance(true);


                Invoice invoice = new Invoice();
                invoice.setAccount(account);
                invoice.setType(Invoice.Type.TUITION_FEES);
                invoice.setAmount(course.getFee());
                invoice.setDueDate(LocalDate.now());

                integrationService.createFinancialAccount(account);
                integrationService.createLibraryAccount(account);
                integrationService.createCourseFeeInvoice(invoice);
            }
        }
        catch (Exception e){
            System.out.println("Account creation failed: " + e.getMessage());
        }




    }

}
