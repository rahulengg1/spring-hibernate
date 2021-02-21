package com.rahul.inheritance.table.per_class.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "walking_customer")
public class WalkingCustomer extends Customer {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
