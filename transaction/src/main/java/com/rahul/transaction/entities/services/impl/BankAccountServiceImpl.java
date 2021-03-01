package com.rahul.transaction.entities.services.impl;

import com.rahul.transaction.entities.BankAccount;
import com.rahul.transaction.entities.repo.BankAccountRepository;
import com.rahul.transaction.entities.services.BankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService {


    private final BankAccountRepository bankAccountRepository;


    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public void transfer(int transferFrom, int transferTo, int amount) {

        BankAccount firstCustomerAccount = bankAccountRepository.getOne(transferFrom);
        firstCustomerAccount.setBalance(firstCustomerAccount.getBalance() - amount);
        BankAccount savedCustomerAccount = bankAccountRepository.save(firstCustomerAccount);

        if (savedCustomerAccount.getBalance() < 500) {
            throw new RuntimeException("Amount larger than 500");
        }

        BankAccount secondCustomerAccount = bankAccountRepository.getOne(transferTo);
        secondCustomerAccount.setBalance(secondCustomerAccount.getBalance() + amount);
        bankAccountRepository.save(secondCustomerAccount);


    }
}
