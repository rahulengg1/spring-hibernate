package com.rahul.composite.embedded.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StudentId implements Serializable {


    private int id;
    private String emailId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
