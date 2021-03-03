package com.rahul.composite.entities.repo;

import com.rahul.composite.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
}
