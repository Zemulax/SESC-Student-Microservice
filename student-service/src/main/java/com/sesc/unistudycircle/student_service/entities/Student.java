package com.sesc.unistudycircle.student_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String externalStudentId;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> coursesEnrolledIn = new HashSet<>();

    public void enrollCourse(Course course) {
        if (!coursesEnrolledIn.contains(course)) {
            coursesEnrolledIn.add(course);
        }
        coursesEnrolledIn = new HashSet<>();
    }

}
