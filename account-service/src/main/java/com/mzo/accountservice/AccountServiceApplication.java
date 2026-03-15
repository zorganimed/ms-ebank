package com.mzo.accountservice;

import com.mzo.accountservice.clients.CustomerRestClient;
import com.mzo.accountservice.entities.BankAccount;
import com.mzo.accountservice.enums.AccountType;
import com.mzo.accountservice.model.Customer;
import com.mzo.accountservice.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService service, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.findAllCustomers().forEach(customer -> {
                for (int j = 0; j <3; j++) {
                    service.saveBankAccount(BankAccount.builder()
                            .accountId(UUID.randomUUID().toString())
                            .currency(Math.random() > 0.5 ? "TND" :
                                    (Math.random()+1) > 1.2 ? "EUR":"USD")
                            .balance(9000+Math.random()*3000)
                            .createAt(LocalDate.now())
                            .customerId(Long.valueOf(customer.getId()))
                            .type(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .build());
                }
            });
        };
    }
}
