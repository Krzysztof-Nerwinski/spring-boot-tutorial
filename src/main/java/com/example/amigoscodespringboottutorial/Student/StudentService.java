package com.example.amigoscodespringboottutorial.Student;

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

    private void validateStudentRequiredFields(Student student) {
        if (student.getName() == null || student.getName().length() == 0)
            throw new IllegalStateException("Student must have a name");
        if (student.getEmail() == null || student.getEmail().length() == 0)
            throw new IllegalStateException("Student must have an email address");
        if (student.getDob() == null)
            throw new IllegalStateException("Student must have a birthday");
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent())
            throw new IllegalStateException("email taken");

        validateStudentRequiredFields(student);

        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exist");
        }
        studentRepository.deleteById(id);
    }

//    @Transactional
//    public void updateStudent(Long id, String name, String email) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException("student with " +
//                "id " + id + " does not exist"));
//        if (student.getName() != null && student.getEmail().length() > 0 && !Objects.equals(student.getName(),
//            existingStudent.getName())) {
//        existingStudent.setName(student.getName());
//    }
//        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(student.getEmail(),
//            existingStudent.getEmail())) {
//        existingStudent.setEmail(student.getEmail());
//    }
//        if (student.getDob() != null && !Objects.equals(student.getDob(), existingStudent.getDob())) {
//        existingStudent.setDob(student.getDob());
//    }
//        studentRepository.save(student);
//    }

    public void replaceStudent(Long id, Student student) {
        if (student.getId() != null && !Objects.equals(student.getId(), id))
            throw new IllegalStateException("If provided body id must match path id");

        Student existingStudent =
                studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                        "student with id %d does not exist".formatted(id)));

        validateStudentRequiredFields(student);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDob(student.getDob());

        studentRepository.save(existingStudent);
    }
}
