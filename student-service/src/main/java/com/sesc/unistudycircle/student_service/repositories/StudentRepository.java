package com.sesc.unistudycircle.student_service.repositories;

import com.sesc.unistudycircle.student_service.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);

    List<Student> findByEmail(String email);

    boolean existsByStudentId(String studentId);

    Student findByStudentId(String studentId);
}
