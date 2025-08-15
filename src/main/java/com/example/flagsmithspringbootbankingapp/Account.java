package com.example.flagsmithspringbootbankingapp;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Account {
    String id;
    String accountHolderName;
    double balance;

    public Account(String accountHolderName) {
        this.id = UUID.randomUUID().toString();
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }
}

@Getter
@Setter
class AddMoneyRequest {
    String accountId;
    double amount;
}


@Getter
@Setter
class WithdrawMoneyRequest {
    String accountId;
    double amount;
}