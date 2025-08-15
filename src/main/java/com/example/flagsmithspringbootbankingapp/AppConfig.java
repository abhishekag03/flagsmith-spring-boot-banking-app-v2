package com.example.flagsmithspringbootbankingapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}