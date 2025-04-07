package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course createCourse(Course course);
    void enrollCourse(Long courseId, Long studentId);
    List<Course> getCourseByStudentId(Long studentId);
}
