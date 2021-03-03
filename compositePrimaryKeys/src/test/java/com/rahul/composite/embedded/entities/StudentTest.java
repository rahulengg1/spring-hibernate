package com.rahul.composite.embedded.entities;

import com.rahul.composite.embedded.entities.repo.StudentRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.org.bouncycastle.est.LimitedSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class StudentTest {

    @Autowired
    StudentRepo studentRepo;


    @Test
    public void saveStudent()
    {
        StudentId studentId = new StudentId();
        studentId.setId(1);
        studentId.setEmailId("test@student.com");

        Student student= new Student();
        student.setStudentId(studentId);
        student.setName("test student");

        studentRepo.save(student);

        List<Student> students = studentRepo.findAll();
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getStudentId().getId()).isEqualTo(1);

    }
}