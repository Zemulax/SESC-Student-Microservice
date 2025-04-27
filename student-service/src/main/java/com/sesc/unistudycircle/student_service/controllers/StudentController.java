package com.sesc.unistudycircle.student_service.controllers;

import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/student")
@EnableWebMvc
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //for registering a new student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }



    //returns a list of all existing students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    //get a specific student according to the Id
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //authenticate a student through email and password
    @PostMapping("/authenticate")
    public ResponseEntity<Student> getStudentByEmailAndPassword(@RequestBody Student authstudent) {
        Student student = studentService.getStudentByEmailAndPassword(authstudent.getEmail(), authstudent.getPassword());
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    //these need String studentId too
        @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentId, @RequestBody Student updatedstudent) {
        Student student = studentService.updateStudentById(studentId, updatedstudent);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //delete a student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable String studentId) {
        studentService.deleteStudentByStudentId(studentId);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

}

