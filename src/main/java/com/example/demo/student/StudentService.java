package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
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

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email is already taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentID) {
        boolean exists = studentRepository.existsById(studentID);
        if(!exists){
            throw new IllegalStateException("Student with ID "+studentID+" does not exist");
        }
        studentRepository.deleteById(studentID);

    }

    @Transactional  //Entity goes into manage state, no need of queries
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id "+studentId+" does not exist"));

        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("This email has already been taken!");
            }
            student.setEmail(email);
        }
    }
}
