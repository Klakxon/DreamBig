package com.example.dreambig;

import com.example.DreamBig.entity.User;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.context.TestConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(ValidationServiceTest.ManualTestConfig.class)
public class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    public void setUp() {
        validationService = new ValidationServiceImpl();
    }

    @TestConfiguration
    static class ManualTestConfig {
        @Bean
        public ValidationService validationService() {
            return new ValidationServiceImpl();
        }
    }

    @Test
    public void whenValidPhoneNumber_thenReturnTrue() {
        String validPhoneNumber = "+380123456789";
        assertThat(validationService.isValidPhoneNumber(validPhoneNumber)).isTrue();
    }

    @Test
    public void whenInvalidPhoneNumber_thenReturnFalse() {
        String invalidPhoneNumber = "123-456";
        assertThat(validationService.isValidPhoneNumber(invalidPhoneNumber)).isFalse();
    }

    @Test
    public void whenValidEmail_thenReturnTrue() {
        String validEmail = "test@example.com";
        assertThat(validationService.isValidEmail(validEmail)).isTrue();
    }

    @Test
    public void whenInvalidEmail_thenReturnFalse() {
        String invalidEmail = "test.com";
        assertThat(validationService.isValidEmail(invalidEmail)).isFalse();
    }

    @Test
    public void whenPasswordMatches_thenReturnTrue() {
        User user = new User((long) 1, "Andrii Kviatkovskyi", "+380123456789", "andrewkviat@example.com", "", "ADMIN");
        user.setPassword("password123");
        String password = "password123";

        assertThat(validationService.checkPassword(user, password)).isTrue();
    }

    @Test
    public void whenPasswordDoesNotMatch_thenReturnFalse() {
        User user = new User((long) 2, "Yelyzaveta Bohun", "+380987654321", "bogunok@example.com", "", "TRAINER");
        user.setPassword("password123");
        String password = "password321";

        assertThat(validationService.checkPassword(user, password)).isFalse();
    }
}
