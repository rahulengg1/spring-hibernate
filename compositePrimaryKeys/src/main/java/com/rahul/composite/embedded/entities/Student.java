package com.rahul.composite.embedded.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity

public class Student {

    @EmbeddedId
    private StudentId studentId;
    private String name;

    public StudentId getStudentId() {
        return studentId;
    }

    public void setStudentId(StudentId studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
