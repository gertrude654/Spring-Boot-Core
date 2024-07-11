package com.spring.SpringCore.service;

import com.spring.SpringCore.models.Student;

import java.util.List;

public interface StudentService {

    Student getStudent(int studentId);
    List<Student> getAllStudents();
    Student createStudent(Student student);
    void updateStudent(Student student);
    boolean deleteStudent(int studentId);
}
