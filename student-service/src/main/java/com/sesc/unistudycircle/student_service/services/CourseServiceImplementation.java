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
    private final EnrolmentService enrolmentService;



    public CourseServiceImplementation(CourseRepository courseRepository,
                                       StudentService studentService,
                                      EnrolmentService enrolmentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.enrolmentService = enrolmentService;

    }

    //get a list of all courses
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    //add a new course
    @Override
    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    //enroll a student
    public void enrollCourse(Long courseId, String studentId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found please"));
        student.getCourses().add(course);

        //create a financial account for enrolled student and an invoice
        enrolmentService.enrollStudent(student, course);
        studentService.saveStudent(student);

    }

    //return all courses associated with this particular studentid
    @Override
    public List<Course> getCourseByStudentId(String studentId) {
        Student student = studentService.getStudentById(studentId);
        return new ArrayList<>(student.getCourses());

    }


}
