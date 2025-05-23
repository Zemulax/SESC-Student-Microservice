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

    //create new student
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    //get student by specified studentID
    @Override
    public Student getStudentById(String studentId) {
        if (studentRepository.existsByStudentId(studentId)) {
            return studentRepository.findByStudentId(studentId);
        }
        else {System.out.println("Student not found");}
        return null;
    }
    //look for a student with a specific email address and password
    //return student object if found, else just null
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

    //delete student by specified ID
    @Override
    public void deleteStudentByStudentId(String studentId) {
        if (studentRepository.existsByStudentId(studentId)) {
            studentRepository.deleteByStudentId(studentId);
        }
        else {System.out.println("Student not found");}
    }

    //update student
    @Override
    public Student updateStudentById(String studentId, Student updatedStudent) {


        //check if student exists first
        if (studentRepository.existsByStudentId(studentId)) {
            //grab the student, we'll use it to extract attributes
            Student student = studentRepository.findByStudentId(studentId);

            Student finalUpdatedStudent = updateHelper(student, updatedStudent);

            //create a copy of student's courses so we can reassign them to the updated student
            Set<Course> courses = new HashSet<>(student.getCourses());

            //first check if the update is for both firstname and last name
            //if yes the copy everything from old object onto the new object except for lname and fname
            if(updatedStudent.getFirstName()!= null && updatedStudent.getLastName() != null) {


                //set old object's id on the new object
                finalUpdatedStudent.setCourses(courses);

                finalUpdatedStudent.setFirstName(updatedStudent.getFirstName());
                finalUpdatedStudent.setLastName(updatedStudent.getLastName());

                return studentRepository.save(finalUpdatedStudent);
            }

            //if update is only for last name
            else if (updatedStudent.getFirstName() != null) {

                finalUpdatedStudent.setFirstName(updatedStudent.getFirstName());
                finalUpdatedStudent.setLastName(student.getLastName());

                return studentRepository.save(finalUpdatedStudent);
            }

            //if update is only for firstname
            else if (updatedStudent.getLastName() != null) {

                finalUpdatedStudent.setLastName(updatedStudent.getLastName());
                finalUpdatedStudent.setFirstName(student.getFirstName());

                return studentRepository.save(finalUpdatedStudent);

            }

        }

        else {System.out.println("Student not found");}

        return updatedStudent;
    }

    public Student updateHelper(Student student, Student updatedStudent) {
        updatedStudent.setId(student.getId());
        updatedStudent.setStudentId(student.getStudentId());
        updatedStudent.setCourses(student.getCourses());

        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setPassword(student.getPassword());

        return updatedStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
