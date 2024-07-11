package com.spring.SpringCore.service;

import com.spring.SpringCore.models.Student;
import com.spring.SpringCore.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsImpl implements StudentService {

    private StudentRepo repo;


    @Autowired
    public StudentsImpl(@Qualifier("studentRepo") StudentRepo repo) {
        this.repo = repo;
    }

    @Override
    public Student getStudent(int studentId) {
        Optional<Student> result = repo.findById(studentId);

        Student theStudent = null;
        if (result.isPresent()) {
            theStudent = result.get();
        }else {
            throw new RuntimeException("Student id not found");
        }
        return theStudent;
    }
    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        return repo.save(student);

    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        repo.save(student);
    }

    @Override
    @Transactional
    public boolean deleteStudent(int StudentId) {
        Optional<Student> deletedStudent = repo.findById(StudentId);
        if (deletedStudent.isPresent()) {
            repo.delete(deletedStudent.get());
            return true;
        }
        return false;
    }
}
