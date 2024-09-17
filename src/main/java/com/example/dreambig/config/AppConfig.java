package com.example.dreambig.config;

import com.example.dreambig.service.implementations.SessionServiceImpl;
import com.example.dreambig.service.implementations.TrainerServiceImpl;
import com.example.dreambig.service.interfaces.SessionService;
import com.example.dreambig.service.interfaces.TrainerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public SessionService sessionService() {
        return new SessionServiceImpl();
    }

    @Bean
    public TrainerService trainerService(SessionService sessionService) {
        return new TrainerServiceImpl(sessionService);
    }
}
