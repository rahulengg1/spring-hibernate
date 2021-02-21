package com.rahul.inheritance.single.table.entities.repository;

import com.rahul.inheritance.single.table.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
