package com.example.amigoscodespringboottutorial;

import com.example.amigoscodespringboottutorial.Student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class AmigosCodeSpringBootTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmigosCodeSpringBootTutorialApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

}
