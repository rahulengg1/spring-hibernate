package com.rahul.transaction.entities.services;

public interface BankAccountService {

    void transfer(int transferFrom, int transferTo, int amount);

}
