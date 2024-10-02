package com.example.dreambig;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.implementations.PhoneVerificationService;
import com.example.DreamBig.service.implementations.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PhoneVerificationService phoneVerificationService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Ініціалізація моків
        user = new UserEntity();
        user.setPhoneNumber("1234567890");
    }

    @Test
    void testCreateUserWithValidPhoneNumber() throws Exception {
        // Мокати, що API повертає true (номер валідний)
        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenReturn(true);

        // Мокати збереження користувача
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Викликати метод
        UserEntity result = userService.createUser(user);

        // Перевірити, чи був викликаний метод перевірки номера
        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        // Перевірити, чи встановився статус "валідного" номера
        assertTrue(result.isPhoneNumberValid());
    }

    @Test
    void testCreateUserWithInvalidPhoneNumber() throws Exception {
        // Мокати, що API повертає false (номер не валідний)
        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenReturn(false);

        // Мокати збереження користувача
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Викликати метод
        UserEntity result = userService.createUser(user);

        // Перевірити, чи був викликаний метод перевірки номера
        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        // Перевірити, чи встановився статус "невалідного" номера
        assertFalse(result.isPhoneNumberValid());
    }

    @Test
    void testCreateUserWithPhoneVerificationException() throws Exception {
        // Мокати, що API викидає виключення
        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenThrow(new RuntimeException("API Error"));

        // Мокати збереження користувача
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        // Викликати метод і переконатися, що не виникло виключень
        UserEntity result = userService.createUser(user);

        // Перевірити, чи був викликаний метод перевірки номера
        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        // У даному випадку номер має залишитися невалідним через помилку перевірки
        assertFalse(result.isPhoneNumberValid());
    }
}

