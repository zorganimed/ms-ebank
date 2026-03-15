package com.mzo.accountservice.web;

import com.mzo.accountservice.clients.CustomerRestClient;
import com.mzo.accountservice.entities.BankAccount;
import com.mzo.accountservice.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankAccountController {

    private BankAccountService service;

    public BankAccountController(BankAccountService service) {
        this.service = service;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount findBankAccountById(@PathVariable String id){
        return service.findBankAccountBYId(id);
    }

    @GetMapping("/accounts")
    public List<BankAccount> findAllBankAccounts(){
       return service.findAllBankAccounts();
    }

    @PostMapping("/accounts")
    public BankAccount saveBankAccount(@RequestBody BankAccount bankAccount){
        return service.saveBankAccount(bankAccount);
    }
}
