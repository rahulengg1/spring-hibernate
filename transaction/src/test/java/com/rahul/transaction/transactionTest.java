package com.rahul.transaction;

import com.rahul.transaction.entities.BankAccount;
import com.rahul.transaction.entities.repo.BankAccountRepository;
import com.rahul.transaction.entities.services.BankAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class transactionTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    int first_customer_id;
    int second_customer_id;

    @BeforeEach
    public void setup() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(1000);
        bankAccount.setFirstName("customer");
        bankAccount.setLastName("first");
        first_customer_id = bankAccountRepository.save(bankAccount).getAccountNo();

        BankAccount bankAccountTwo = new BankAccount();
        bankAccountTwo.setBalance(1000);
        bankAccountTwo.setFirstName("customer");
        bankAccountTwo.setLastName("second");
        second_customer_id = bankAccountRepository.save(bankAccountTwo).getAccountNo();
    }

    @Test
    @Transactional
    public void testTransfer() {

        int balance_customer_one;
        int balance_customer_second;

        bankAccountService.transfer(first_customer_id, second_customer_id, 400);
        BankAccount bankAccountFirst = bankAccountRepository.getOne(first_customer_id);
        balance_customer_one = bankAccountFirst.getBalance();

        BankAccount bankAccountSecond = bankAccountRepository.getOne(second_customer_id);
        balance_customer_second = bankAccountSecond.getBalance();

        Assertions.assertEquals(600, balance_customer_one);
        Assertions.assertEquals(1400, balance_customer_second);
    }


}
