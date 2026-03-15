package com.mzo.accountservice.entities;

import com.mzo.accountservice.enums.AccountType;
import com.mzo.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {

    @Id
    private String accountId;
    private double balance;
    private LocalDate createAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
