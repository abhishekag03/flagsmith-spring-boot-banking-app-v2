package com.example.flagsmithspringbootbankingapp;

import com.flagsmith.FlagsmithClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AccountService accountService() {
        return new AccountService();
    }

    @Bean
    public FlagsmithClient flagsmithClient() {
        return FlagsmithClient
                .newBuilder()
                .setApiKey("ser.***")
                .build();
    }
}