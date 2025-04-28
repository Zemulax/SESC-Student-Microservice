package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudentServiceImplementationTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImplementation studentServiceImplementation;

    private Student student;
    private String studentId;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setFirstName("mozay");
        student.setLastName("mozay");
        student.setEmail("mozay@gmail.com");
        student.setPassword("goodPassword");
        student.setCourses(new HashSet<>());
        studentId = student.getStudentId();
    }


    @Test
    void saveStudent_ReturnsSavedStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentServiceImplementation.saveStudent(student);
        studentId = savedStudent.getStudentId();
        assertNotNull(savedStudent);
        assertEquals(studentId, savedStudent.getStudentId());
    }

    @Test
    void getStudentByStudentId() {
        when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
        when(studentRepository.findByStudentId(studentId)).thenReturn(student);

        Student student = studentServiceImplementation.getStudentById(studentId);

        assertNotNull(student);
        assertEquals(studentId, student.getStudentId());

    }

    @Test
    void getStudentByEmailAndPassword() {
        String email = "mozay@gmail.com";
        String password = "goodPassword";
        when(studentRepository.existsByEmail(email)).thenReturn(true);
        when(studentRepository.findByEmail(email)).thenReturn(Collections.singletonList(student));

        Student student = studentServiceImplementation.getStudentByEmailAndPassword(email, password);

        assertNotNull(student);
        assertEquals(email, student.getEmail());
    }

    @Test
    void getStudentByEmailAndPassword_And_WhenStudentNotFoundReturnsNull() {
        String email = "nonExistent@gmail.com";
        String password = "goodPassword";
        when(studentRepository.existsByEmail(email)).thenReturn(false);

        Student student = studentServiceImplementation.getStudentByEmailAndPassword(email, password);

        assertNull(student);

    }

    @Test
    void getStudentByEmailAndPassword_WhenPasswordIncorrectReturnsNull() {
        String email = "mozay@gmail.com";
        String password = "incorrectPassword";

        when(studentRepository.existsByEmail(email)).thenReturn(true);
        when(studentRepository.findByEmail(email)).thenReturn(Collections.singletonList(student));

        Student student = studentServiceImplementation.getStudentByEmailAndPassword(email, password);

        assertNull(student);

    }

    @Test
    void deleteStudentByStudentId() {

        when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
        doNothing().when(studentRepository).deleteByStudentId(studentId);

        studentServiceImplementation.deleteStudentByStudentId(studentId);

        when(studentRepository.existsByStudentId(studentId)).thenReturn(false);

        assertFalse(studentRepository.existsByStudentId(studentId));

    }

    @Test
    void updateStudentByStudentId_UsingFullUpdate() {
        Student updatedStudent = new Student();
        updatedStudent.setFirstName("Cuban");
        updatedStudent.setLastName("Cuban");


        when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
        when(studentRepository.findByStudentId(studentId)).thenReturn(updatedStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student finalUpdatedStudent = studentServiceImplementation.updateStudentById(studentId, updatedStudent);

        assertNotNull(finalUpdatedStudent);
        assertEquals("Cuban", finalUpdatedStudent.getFirstName());
        assertEquals("Cuban", finalUpdatedStudent.getLastName());
        assertEquals(updatedStudent.getStudentId(), finalUpdatedStudent.getStudentId());
    }

    @Test
    void updateStudentByStudentId_OnlyUpdatingFirstName(){
        Student updatedStudent = new Student();
        updatedStudent.setFirstName("Mozay2");

        when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
        when(studentRepository.findByStudentId(studentId)).thenReturn(updatedStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student finalUpdatedStudent = studentServiceImplementation.updateStudentById(studentId, updatedStudent);

        assertNotNull(finalUpdatedStudent);
        assertEquals("Mozay2", finalUpdatedStudent.getFirstName());
        assertEquals(updatedStudent.getStudentId(), finalUpdatedStudent.getStudentId());
    }

    @Test
    void updateStudentByStudentId_OnlyUpdatingLastName(){
        Student updatedStudent = new Student();
        updatedStudent.setLastName("Harazi");

        when(studentRepository.existsByStudentId(studentId)).thenReturn(true);
        when(studentRepository.findByStudentId(studentId)).thenReturn(updatedStudent);
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student finalUpdatedStudent = studentServiceImplementation.updateStudentById(studentId, updatedStudent);

        assertNotNull(finalUpdatedStudent);
        assertEquals("Harazi", finalUpdatedStudent.getLastName());
        assertEquals(updatedStudent.getStudentId(), finalUpdatedStudent.getStudentId());
    }


    @Test
    void getAllStudents() {
        List<Student> students = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> allStudents = studentServiceImplementation.getAllStudents();

        assertNotNull(allStudents);
    }
}