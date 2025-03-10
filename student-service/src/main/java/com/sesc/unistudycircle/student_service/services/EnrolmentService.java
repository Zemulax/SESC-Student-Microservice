package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class EnrolmentService {
    private final StudentRepository studentRepository;

    private final IntegrationService integrationService;

    public EnrolmentService(StudentRepository studentRepository,
                            IntegrationService integrationService) {
        this.studentRepository = studentRepository;
        this.integrationService = integrationService;
    }

    public void enrollStudent(Student student, Course course, Invoice invoice) {
        student.enrollCourse(course);
        studentRepository.save(student);
        integrationService.createCourseFeeInvoice(invoice);

    }

}
