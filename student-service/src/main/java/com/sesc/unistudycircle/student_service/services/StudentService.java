package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(String studentId);
    void deleteStudentById(Long id);
    Student updateStudentById(String studentId, Student updatedStudent);
    Student getStudentByEmailAndPassword(String email, String password);
    List<Student> getAllStudents();

}
