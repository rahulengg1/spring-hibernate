package com.rahul.inheritance.table.per_class.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "online_customer")
public class OnlineCustomer extends Customer {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
