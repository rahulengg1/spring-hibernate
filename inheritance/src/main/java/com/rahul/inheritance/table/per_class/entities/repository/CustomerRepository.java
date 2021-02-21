package com.rahul.inheritance.table.per_class.entities.repository;

import com.rahul.inheritance.table.per_class.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
