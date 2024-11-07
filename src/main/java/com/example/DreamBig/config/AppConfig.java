package com.example.DreamBig.config;

import com.example.DreamBig.security.JwtUtil;
import com.example.DreamBig.service.implementations.*;
import com.example.DreamBig.service.interfaces.GreetingService;
import com.example.DreamBig.service.interfaces.SessionService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public static SessionService sessionService() {
        System.out.println("SessionService is being created...");
        return new SessionServiceImpl();
    }

    @Bean
    public GreetingService greetingService() {
        System.out.println("GreetingService is being created...");
        return new GreetingServiceDefault();
    }

    @Bean
    public static TrainerService trainerService(SessionService sessionService) {
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        trainerService.setSessionService(sessionService);
        return trainerService;
    }

    @Bean
    @ConditionalOnProperty(name = "country", havingValue = "Ukraine")
    public GreetingService greetingServiceUkraine() {
        return new GreetingServiceUkraine();
    }

    @Bean
    @ConditionalOnMissingBean(GreetingService.class)
    public GreetingService greetingServiceDefault() {
        return new GreetingServiceDefault();
    }

    @Bean
    @ConditionalOnExpression("#{T(java.lang.Math).random() < 0.001}")
    public LuckyUserService luckyUserService() {
        return new LuckyUserService();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}