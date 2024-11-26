package com.example.DreamBig.config;

import com.example.DreamBig.security.JwtUtil;
import com.example.DreamBig.service.implementations.*;
import com.example.DreamBig.service.interfaces.SessionService;
import com.example.DreamBig.service.interfaces.TrainerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example.DreamBig.aspect")
public class AppConfig {

    @Bean
    public static SessionService sessionService() {
        System.out.println("SessionService is being created...");
        return new SessionServiceImpl();
    }

    @Bean
    public static TrainerService trainerService(SessionService sessionService) {
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        trainerService.setSessionService(sessionService);
        return trainerService;
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