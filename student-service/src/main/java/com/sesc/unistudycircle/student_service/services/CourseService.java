package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course createCourse(Course course);
    void enrollCourse(Long courseId, String studentId);
    List<Course> getCourseByStudentId(String studentId);
}
