package com.example.amigoscodespringboottutorial.Student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student jabba = new Student(
                    "Jabba",
                    LocalDate.of(2023-150, Month.JANUARY, 1),
                    "jabba@mail.com");
            Student bobba = new Student(
                    "Bobba",
                    LocalDate.of(2023-50, Month.FEBRUARY, 2),
                    "bobba@mail.com");

            repository.saveAll(List.of(jabba, bobba));
        };
    }
}
