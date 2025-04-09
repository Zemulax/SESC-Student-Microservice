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

            //check if student has an account already
            Account account = integrationService.getStudentAccount(student.getStudentId());

            //if yes, just create an invoice for the enrolled course
            if(account != null) {

                Invoice invoice = new Invoice();
                invoice.setAccount(account);
                invoice.setType(Invoice.Type.TUITION_FEES);
                invoice.setAmount(course.getFee());
                invoice.setDueDate(LocalDate.now());

                Invoice invoiceRef = integrationService.createCourseFeeInvoice(invoice);
                student.getInvoiceReferences().add(invoiceRef.getReference());
            }
            else {
                //else create an account first then attch an invoice
                Account account1 = new Account();

                account1.setStudentId(student.getStudentId());

                Invoice invoice = new Invoice();
                invoice.setAccount(account1);
                invoice.setType(Invoice.Type.TUITION_FEES);
                invoice.setAmount(course.getFee());
                invoice.setDueDate(LocalDate.now());

                integrationService.createFinancialAccount(account1);
                integrationService.createLibraryAccount(account1);

                Invoice invoiceRef = integrationService.createCourseFeeInvoice(invoice);
                student.getInvoiceReferences().add(invoiceRef.getReference());

            }
        }
        catch (Exception e){
            System.out.println("Account creation failed: "+ e.getMessage());
        }

    }

}
