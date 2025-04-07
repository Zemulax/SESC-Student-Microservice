package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImplementation(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    //enroll a student
    public void enrollCourse(Long courseId, Long studentId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        studentService.saveStudent(student);
        //enrolmentService.enrollStudent(student, course);
    }

    //return all courses associated with this particular studentid
    @Override
    public List<Course> getCourseByStudentId(Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ArrayList<>(student.getCourses());

    }


}
