package com.sesc.unistudycircle.student_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double fee;

    @JsonIgnore
    @ManyToMany(mappedBy = "coursesEnrolledIn", fetch = FetchType.LAZY)
    private Set<Student> studentsEnrolledInCourse = new HashSet<>();
}
