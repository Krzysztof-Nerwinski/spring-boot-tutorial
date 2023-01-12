package com.example.amigoscodespringboottutorial.Student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentService studentService;


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("")
    public ResponseEntity<Student> registerNewStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Student> replaceStudent(@PathVariable("id") Long id,
                                                  @Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.replaceStudent(id, student), HttpStatus.OK);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
                                                 @Valid @RequestBody Student student) {
        // todo: change validation so not entire resource must be supplied
        System.out.println(student.toString());
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.OK);
    }

}
