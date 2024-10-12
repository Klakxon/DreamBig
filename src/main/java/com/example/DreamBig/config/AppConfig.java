package com.example.DreamBig.config;

import com.example.DreamBig.service.implementations.SessionServiceImpl;
import com.example.DreamBig.service.implementations.TrainerServiceImpl;
import com.example.DreamBig.service.interfaces.SessionService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public static SessionService sessionService() {
        return new SessionServiceImpl();
    }

    @Bean
    public static TrainerService trainerService(SessionService sessionService) {
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        trainerService.setSessionService(sessionService);
        return trainerService;
    }
}