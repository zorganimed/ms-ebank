package com.mzo.accountservice.services;

import com.mzo.accountservice.entities.BankAccount;

import java.util.List;

public interface BankAccountService {

    BankAccount findBankAccountBYId(String accountId);
    List<BankAccount> findAllBankAccounts();
    BankAccount saveBankAccount(BankAccount bankAccount);
}
