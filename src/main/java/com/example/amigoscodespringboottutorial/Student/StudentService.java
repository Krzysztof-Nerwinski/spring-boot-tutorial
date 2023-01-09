package com.example.amigoscodespringboottutorial.Student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Jabba",
                        32,
                        LocalDate.of(1990, Month.FEBRUARY, 28),
                        "some_main@mail.com")
        );
    }

}
