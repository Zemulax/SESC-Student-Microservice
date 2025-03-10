package com.sesc.unistudycircle.student_service;

import com.sesc.unistudycircle.student_service.entities.Account;
import com.sesc.unistudycircle.student_service.entities.Course;
import com.sesc.unistudycircle.student_service.entities.Invoice;
import com.sesc.unistudycircle.student_service.entities.Student;
import com.sesc.unistudycircle.student_service.repositories.StudentRepository;
import com.sesc.unistudycircle.student_service.services.EnrolmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.client.RestClientBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Set;


@Configuration
public class MiscellaneousBeans {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

        @Bean
        CommandLineRunner initDatabase(StudentRepository studentRepository, EnrolmentService enrolmentService) {
            return args -> {

                Account acc1 = new Account();

                acc1.setStudentId("c77791");
                acc1.setHasOutstandingBalance(false);

                Invoice invoice1 = new Invoice();
                invoice1.setAmount(1738);
                invoice1.setDueDate(LocalDate.of(2025, 2, 23));
                invoice1.setType(Invoice.Type.LIBRARY_FINE);
                invoice1.setAccount(acc1);


                Course sesc = new Course();
                sesc.setTitle("SESC");
                sesc.setDescription("Software Engineering for Service Computing");
                sesc.setFee(10.00);

                Course ase = new Course();
                ase.setTitle("ASE");
                ase.setDescription("Advanced Software Engineering Software Engineering");
                ase.setFee(100.00);

                Student cuban = new Student();
                cuban.setFirstName("cuban");
                cuban.setLastName("zem");
                cuban.setExternalStudentId("c77791");
                //cuban.setCoursesEnrolledIn(Set.of(sesc, ase));
                cuban.enrollCourse(sesc);

                Student mozay = new Student();
                mozay.setFirstName("mozay");
                mozay.setLastName("mozay");
                mozay.setExternalStudentId("1234");

                //mozay.setCoursesEnrolledIn(Set.of(ase));
                mozay.enrollCourse(sesc);


                //Set<Course> courses = cuban.getCoursesEnrolledIn();
               // courses.add(sesc);
                //courses.add(course2);

                enrolmentService.enrollStudent(cuban,sesc,invoice1);

                studentRepository.saveAllAndFlush(Set.of(cuban, mozay));
            };
        }
    }
