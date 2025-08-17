package com.example.flagsmithspringbootbankingapp;

import com.flagsmith.FlagsmithClient;
import com.flagsmith.config.FlagsmithConfig;
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
        FlagsmithConfig flagsmithConfig = FlagsmithConfig
                .newBuilder()
                .withLocalEvaluation(true)
                .withEnvironmentRefreshIntervalSeconds(60)
                .build();
        return FlagsmithClient
                .newBuilder()
                .setApiKey("ser.****")
                .withConfiguration(flagsmithConfig)
                .build();
    }
}