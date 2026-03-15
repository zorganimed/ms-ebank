package com.mzo.accountservice.services.impl;

import com.mzo.accountservice.clients.CustomerRestClient;
import com.mzo.accountservice.entities.BankAccount;
import com.mzo.accountservice.model.Customer;
import com.mzo.accountservice.repositories.BankAccountRepository;
import com.mzo.accountservice.services.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository repository;
    private CustomerRestClient customerRestClient;


    public BankAccountServiceImpl(BankAccountRepository repository, CustomerRestClient customerRestClient) {
        this.repository = repository;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public BankAccount findBankAccountBYId(String accountId) {
        BankAccount bankAccount = repository.findById(accountId)
                .orElseThrow(()-> new RuntimeException("Bank account not found"));

        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAllBankAccounts() {
        List<BankAccount> accounts =  repository.findAll();
        accounts.forEach(account ->{
            Customer customer = customerRestClient.findCustomerById(account.getCustomerId());
            account.setCustomer(customer);
        });
        return accounts;
    }

    @Override
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        return repository.save(bankAccount);
    }
}
