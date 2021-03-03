package com.rahul.composite.entities;

import java.io.Serializable;

public class CustomerId implements Serializable {



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
