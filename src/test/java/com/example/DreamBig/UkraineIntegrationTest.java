package com.example.dreambig;

import com.example.DreamBig.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AppConfig.class)
public class UkraineIntegrationTest {

    @Autowired
    private GreetingService greetingService;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("country", () -> "Ukraine");
    }

    @Test
    public void whenCountryUkraine_thenGreetingServiceUkraineIsLoaded() {
        assertThat(greetingService.greet())
                .isEqualTo("Слава Україні! Ласкаво просимо у додаток мережі спортклубів DreamBig!");
    }
}
