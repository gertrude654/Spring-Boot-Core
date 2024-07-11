package com.spring.SpringCore;

import com.spring.SpringCore.models.Student;
import com.spring.SpringCore.repository.StudentRepo;
import com.spring.SpringCore.service.StudentsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class StudentTest {



        @Mock
        private StudentRepo studentRepo;

        @InjectMocks
        private StudentsImpl studentsImpl;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testGetStudent() {
            Student student = new Student();
            student.setId(1);
            student.setName("John Doe");

            when(studentRepo.findById(1)).thenReturn(Optional.of(student));

            Student result = studentsImpl.getStudent(1);
            assertNotNull(result);
            assertEquals("John Doe", result.getName());
        }

        @Test
        void testGetAllStudents() {
            Student student1 = new Student();
            student1.setId(1);
            student1.setName("John Doe");

            Student student2 = new Student();
            student2.setId(2);
            student2.setName("Jane Doe");

            List<Student> students = Arrays.asList(student1, student2);

            when(studentRepo.findAll()).thenReturn(students);

            List<Student> result = studentsImpl.getAllStudents();
            assertNotNull(result);
            assertEquals(2, result.size());
        }

        @Test
        void testCreateStudent() {
            Student student = new Student();
            student.setName("John Doe");

            when(studentRepo.save(any(Student.class))).thenReturn(student);

            Student result = studentsImpl.createStudent(student);
            assertNotNull(result);
            assertEquals("John Doe", result.getName());
        }

        @Test
        void testUpdateStudent() {
            Student student = new Student();
            student.setId(1);
            student.setName("John Doe");

            when(studentRepo.save(any(Student.class))).thenReturn(student);

            studentsImpl.updateStudent(student);
            verify(studentRepo, times(1)).save(student);
        }

        @Test
        void testDeleteStudent() {
            Student student = new Student();
            student.setId(1);
            student.setName("John Doe");

            when(studentRepo.findById(1)).thenReturn(Optional.of(student));

            boolean result = studentsImpl.deleteStudent(1);
            assertTrue(result);
            verify(studentRepo, times(1)).delete(student);
        }

        @Test
        void testDeleteStudent_NotFound() {
            when(studentRepo.findById(1)).thenReturn(Optional.empty());

            boolean result = studentsImpl.deleteStudent(1);
            assertFalse(result);
            verify(studentRepo, times(0)).delete(any(Student.class));
        }
    }
