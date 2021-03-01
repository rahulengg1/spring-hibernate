package com.rahul.transaction.entities.repo;

import com.rahul.transaction.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
