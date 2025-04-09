package com.sesc.unistudycircle.student_service.controllers;


import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //return all available courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCoursesOffered() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    //add a course
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    //enroll student into course
    @PostMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Void> enrollCourse(
            @PathVariable Long courseId,
            @PathVariable String studentId) {
        courseService.enrollCourse(courseId, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //get course with this specific studentid
    @GetMapping("/{studentId}")
    public ResponseEntity<List<Course>> getCourseById(@PathVariable String studentId) {
        List<Course> courses = courseService.getCourseByStudentId(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
