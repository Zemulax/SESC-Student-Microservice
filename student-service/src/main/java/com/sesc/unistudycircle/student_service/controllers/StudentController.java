package com.sesc.unistudycircle.student_service.controllers;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.services.StudentService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/student")
@EnableWebMvc
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student, Account account) {
        Student createdStudent = studentService.saveStudent(student, account);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

//    @PostMapping("/create")
//    public EntityModel<Student> createStudent(@RequestBody Student student, Account account) {
//        Student createdStudent = studentService.saveStudent(student, account);
//        return EntityModel.of(createdStudent, linkTo(methodOn(StudentController.class)
//                .createStudent(student,account)).withSelfRel());
//    }


        @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
//    @GetMapping
//    public CollectionModel<Student> getAllStudents() {
//        List<Student> students = studentService.getAllStudents();
//        return CollectionModel.of(students, linkTo(methodOn(StudentController.class)
//                .getAllStudents()).withSelfRel());
//    }


    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


//    @GetMapping("/{studentId}")
//    public EntityModel<Student> getStudentByStudentId(@PathVariable Long studentId) {
//        Student student = studentService.getStudentById(studentId);
//        return EntityModel.of(student, linkTo(methodOn(StudentController.class)
//                .getStudentByStudentId(studentId)).withSelfRel());
//    }

        @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedstudent) {
        Student student = studentService.updateStudentById(studentId, updatedstudent);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
//    @PutMapping("/{studentId}")
//    public EntityModel<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedstudent) {
//        Student student = studentService.updateStudentById(studentId, updatedstudent);
//        return EntityModel.of(student, linkTo(methodOn(StudentController.class)
//                .updateStudent(studentId, updatedstudent)).withSelfRel());
//    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

//    @DeleteMapping("/{studentId}")
//    public EntityModel<Student> deleteStudentById(@PathVariable Long studentId) {
//        Student deletedStudent = studentService.getStudentById(studentId);
//        studentService.deleteStudentById(studentId);
//        return EntityModel.of(deletedStudent, linkTo(methodOn(StudentController.class)
//                .deleteStudentById(studentId)).withSelfRel());
//    }
}


//
//
//    @GetMapping
//    public ModelAndView getStudentsForView() {
//        List<Student> students = studentService.getAllStudents();
//        ModelAndView modelAndView = new ModelAndView("courses");
//        modelAndView.addObject("students", students);
//        return modelAndView;
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//        Student student = studentService.getStudentById(id);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedstudent) {
//        Student student = studentService.updateStudentById(id, updatedstudent);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
//        studentService.deleteStudentById(id);
//        return new ResponseEntity<>("Student deleted", HttpStatus.OK);
//    }
//}
