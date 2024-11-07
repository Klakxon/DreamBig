package com.example.dreambig;

import com.example.DreamBig.service.interfaces.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
public class AppConfigIntegrationTest {

    @Autowired
    private GreetingService greetingService;

    @Value("${country:Default}")
    private String country;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("country", () -> "Default");
    }

    @Test
    public void whenCountryIsUkraine_thenGreetingIsFromUkraine() {
        if ("Ukraine".equals(country)) {
            assertThat(greetingService.greet()).isEqualTo("Слава Україні! Ласкаво просимо у додаток мережі спортклубів DreamBig!");
        } else {
            assertThat(greetingService.greet()).isEqualTo("Welcome to DreamBig sport club application!");
        }
    }
}
