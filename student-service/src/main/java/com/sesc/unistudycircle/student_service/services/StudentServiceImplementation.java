package com.sesc.unistudycircle.student_service.services;

import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String studentId) {
        if (studentRepository.existsByStudentId(studentId)) {
            return studentRepository.findByStudentId(studentId);
        }
        else {System.out.println("Student not found");}
        return null;
    }
    @Override
    public Student getStudentByEmailAndPassword(String email, String password) {
        if (studentRepository.existsByEmail(email)) {
            Student student = studentRepository.findByEmail(email).getFirst();
            if (student.getPassword().equals(password)){
                return student;
            }
            else {System.out.println("Incorrect password or email");}
        }
        else {System.out.println("Student not found as: " + email);}
        return null;
    }

    @Override
    public void deleteStudentById(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
        }
        else {System.out.println("Student not found");}
    }

    @Override
    public Student updateStudentById(String studentId, Student updatedStudent) {

        //check if student exists first
        if (studentRepository.existsByStudentId(studentId)) {
            //grad student, we'll use it to extract attributes
            Student student = studentRepository.findByStudentId(studentId);

            //create a copy of student's courses so we can reassign them to the updated student
            Set<Course> courses = new HashSet<>(student.getCourses());
            System.out.println("Student is: " + student);
            System.out.println("Course is: " + courses);

            //first check if the update is for both firstname and last name
            //if yes the copy everything from old object onto the new object except for lname and fname
            //if not just copy everything except for attribute requesting update
            if(updatedStudent.getFirstName()!= null && updatedStudent.getLastName() != null) {

                //set old object's id on the new object
                updatedStudent.setId(student.getId());
                updatedStudent.setStudentId(student.getStudentId());
                updatedStudent.setCourses(courses);

                updatedStudent.setFirstName(updatedStudent.getFirstName());
                updatedStudent.setLastName(updatedStudent.getLastName());
                updatedStudent.setEmail(student.getEmail());
                updatedStudent.setPassword(student.getPassword());

                return studentRepository.save(updatedStudent);
            }
            else if (updatedStudent.getFirstName() != null) {

                updatedStudent.setId(student.getId());
                updatedStudent.setStudentId(studentId);
                updatedStudent.setCourses(student.getCourses());

                updatedStudent.setFirstName(updatedStudent.getFirstName());
                updatedStudent.setLastName(student.getLastName());
                updatedStudent.setEmail(student.getEmail());
                updatedStudent.setPassword(student.getPassword());

                return studentRepository.save(updatedStudent);
            }
            else if (updatedStudent.getLastName() != null) {

                updatedStudent.setId(student.getId());
                updatedStudent.setStudentId(student.getStudentId());
                updatedStudent.setCourses(student.getCourses());

                updatedStudent.setLastName(updatedStudent.getLastName());
                updatedStudent.setEmail(student.getEmail());
                updatedStudent.setPassword(student.getPassword());
                updatedStudent.setFirstName(student.getFirstName());

                return studentRepository.save(updatedStudent);

            }

        }

        else {System.out.println("Student not found");}

        return updatedStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
