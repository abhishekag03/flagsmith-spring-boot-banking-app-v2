package com.example.flagsmithspringbootbankingapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class FlagsmithSpringBootBankingAppApplication {
    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(FlagsmithSpringBootBankingAppApplication.class, args);
    }
    // endpoint to create a new account
    @PostMapping(path = "/create-account", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody String accountHolderName) {
        return ResponseEntity.ok(accountService.CreateAccount(accountHolderName));
    }

    // endpoint to add money to an account
    @PostMapping(path = "/add-money", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Account addMoney(@RequestBody AddMoneyRequest req) {
        return accountService.AddMoney(req.accountId, req.amount);
    }


    // endpoint to withdraw money from an account
    @PostMapping(path = "/withdraw-money", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> withdrawMoney(@RequestBody WithdrawMoneyRequest req) {
        return ResponseEntity.ok(accountService.WithdrawMoney(req.accountId, req.amount));

    }

    // endpoint to check account balance
    @GetMapping("/check-balance")
    public double checkBalance(@RequestParam(value = "accountId") String accountId) {
        Account account = accountService.GetAccount(accountId);
        return account.balance;
    }

    // endpoint to get the current interest rate
    @GetMapping("/interest-rate")
    public ResponseEntity<Object> getInterestRate() {
        return ResponseEntity.ok(3.5);
    }
}