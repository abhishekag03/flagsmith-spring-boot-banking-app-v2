package com.example.flagsmithspringbootbankingapp;

import java.util.HashMap;

public class AccountService {

    private final HashMap<String, Account> UserAccounts;

    // initialises empty map to store user's accounts info
    public AccountService() {
        UserAccounts = new HashMap<>();
    }

    // get account via accountID
    public Account GetAccount(String accountID)  {
        return this.UserAccounts.get(accountID);
    }

    // create a new account and add it to the map
    public Account CreateAccount(String accountHolderName) {
        Account newAccount = new Account(accountHolderName);
        this.UserAccounts.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    // add money to user's bank account
    public Account AddMoney(String accountId, double amount) {
        Account acc = this.GetAccount(accountId);
        acc.balance += amount;
        return acc;
    }

    // withdraw money from user's bank account
    public Account WithdrawMoney(String accountId, double amount) {
        Account acc = this.GetAccount(accountId);
        acc.balance += amount;
        return acc;
    }
}