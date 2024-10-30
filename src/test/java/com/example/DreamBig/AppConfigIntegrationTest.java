package com.example.DreamBig;

import com.example.DreamBig.config.AppConfig;
import com.example.DreamBig.service.interfaces.GreetingService;
import com.example.DreamBig.service.interfaces.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AppConfig.class)
public class AppConfigIntegrationTest {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private GreetingService greetingService;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("country", () -> "Default");
    }

    @Test
    public void whenContextLoads_thenSessionServiceIsCreated() {
        assertThat(sessionService).isNotNull();
    }

    @Test
    public void whenCountryNotUkraine_thenGreetingServiceDefaultIsLoaded() {
        assertThat(greetingService.greet())
                .isEqualTo("Welcome to DreamBig sport club application!");
    }
}
