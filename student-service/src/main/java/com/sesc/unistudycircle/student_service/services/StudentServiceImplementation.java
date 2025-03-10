package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.exceptions.StudentExistsException;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final IntegrationService integrationService;



    public StudentServiceImplementation(StudentRepository studentRepository, IntegrationService integrationService) {
        this.studentRepository = studentRepository;
        this.integrationService = integrationService;
    }

    @Override
    public Student saveStudent(Student student, Account account) {
        if(account.getStudentId() != student.getExternalStudentId() ) {
            account.setStudentId(student.getExternalStudentId());
            integrationService.createFinancialAccount(account);
            integrationService.createLibraryAccount(account);
        }
        else {
         throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
        //throw new IllegalArgumentException("Topic cannot be creted
        // with invalid student id" + studentIdbyTopic);

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            return studentRepository.findById(studentId).get();
        }
        else {System.out.println("Student not found");}
        return null;
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        }
        else {System.out.println("Student not found");}
    }

    @Override
    public Student updateStudentById(Long studentId, Student updatedStudent) {
        if (studentRepository.existsById(studentId)) {
            updatedStudent.setId(studentId);
            studentRepository.save(updatedStudent);
        }
        else {System.out.println("Student not found");}
        return updatedStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
