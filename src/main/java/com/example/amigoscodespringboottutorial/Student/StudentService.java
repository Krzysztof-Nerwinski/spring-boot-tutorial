package com.example.amigoscodespringboottutorial.Student;

import jakarta.websocket.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
            throw new IllegalStateException("email taken");

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student with " +
                "id " + id + " does not exist"));
        if (student.getName() != null && student.getEmail().length() > 0 && !Objects.equals(student.getName(),
            existingStudent.getName()))
           existingStudent.setName(student.getName());

        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(student.getEmail(),
            existingStudent.getEmail()))
            existingStudent.setEmail(student.getEmail());

        if (student.getDob() != null && !Objects.equals(student.getDob(), existingStudent.getDob()))
            existingStudent.setDob(student.getDob());

        return studentRepository.save(student);
    }

    public Student replaceStudent(Long id, Student student) {
        if (student.getId() != null && !Objects.equals(student.getId(), id))
            throw new IllegalStateException("If provided body id must match path id");

        Student existingStudent =
                studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                        "student with id %d does not exist".formatted(id)));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDob(student.getDob());

        return studentRepository.save(existingStudent);
    }
}
