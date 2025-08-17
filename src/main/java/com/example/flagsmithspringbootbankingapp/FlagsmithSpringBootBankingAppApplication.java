package com.example.flagsmithspringbootbankingapp;

import com.flagsmith.FlagsmithClient;
import com.flagsmith.exceptions.FlagsmithClientError;
import com.flagsmith.models.Flags;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private FlagsmithClient flagsmithClient;

    double defaultInterestRate = 3.5;

    public static void main(String[] args) {
        SpringApplication.run(FlagsmithSpringBootBankingAppApplication.class, args);
    }


    // endpoint to create a new account
    @PostMapping(path = "/create-account", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createAccount(@RequestBody String accountHolderName) throws FlagsmithClientError {
        Flags flags = flagsmithClient.getEnvironmentFlags();
        String featureName = "allow_account_creation";
        Boolean isAllowed = flags.isFeatureEnabled(featureName);
        if (isAllowed) {
            return ResponseEntity.ok(accountService.CreateAccount(accountHolderName));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
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
    public ResponseEntity<Account> withdrawMoney(@RequestBody WithdrawMoneyRequest req) throws FlagsmithClientError {
        Flags flags = flagsmithClient.getEnvironmentFlags();
        String featureName = "allow_withdraw_money";
        Boolean isAllowed = flags.isFeatureEnabled(featureName);
        if (isAllowed) {
            return ResponseEntity.ok(accountService.WithdrawMoney(req.accountId, req.amount));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    // endpoint to check account balance
    @GetMapping("/check-balance")
    public double checkBalance(@RequestParam(value = "accountId") String accountId) {
        Account account = accountService.GetAccount(accountId);
        return account.balance;
    }

    // endpoint to get the current interest rate
    @GetMapping("/interest-rate")
    public ResponseEntity<Object> getInterestRate() throws FlagsmithClientError {
        Flags flags = flagsmithClient.getEnvironmentFlags();
        String featureName = "interest_rate";
        Object intRate = flags.getFeatureValue(featureName);
        if (intRate != "") {
            return ResponseEntity.ok(intRate);
        }
        return ResponseEntity.ok(defaultInterestRate);
    }
}