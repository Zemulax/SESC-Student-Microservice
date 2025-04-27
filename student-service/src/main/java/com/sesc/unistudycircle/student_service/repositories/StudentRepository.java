package com.sesc.unistudycircle.student_service.repositories;

import com.sesc.unistudycircle.student_service.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    //used to return a list of students by email, probably just 1
    List<Student> findByEmail(String email);

    //check if a student exists by specified studentID
    boolean existsByStudentId(String studentId);

    //return student by specified studentID
    Student findByStudentId(String studentId);

    void deleteByStudentId(String studentId);
}
