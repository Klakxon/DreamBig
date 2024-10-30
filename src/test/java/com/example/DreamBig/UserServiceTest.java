package com.example.DreamBig;

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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


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
        MockitoAnnotations.openMocks(this);
        user = new UserEntity();
        user.setPhoneNumber("1234567890");
    }

    @Test
    void testCreateUserWithValidPhoneNumber() throws Exception {

        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenReturn(true);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userService.createUser(user);

        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        assertTrue(result.isPhoneNumberValid());
    }

    @Test
    void testCreateUserWithInvalidPhoneNumber() throws Exception {
        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenReturn(false);

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userService.createUser(user);

        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        assertFalse(result.isPhoneNumberValid());
    }

    @Test
    void testCreateUserWithPhoneVerificationException() throws Exception {

        when(phoneVerificationService.verifyPhoneNumber("1234567890")).thenThrow(new RuntimeException("API Error"));

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userService.createUser(user);

        verify(phoneVerificationService).verifyPhoneNumber("1234567890");

        assertFalse(result.isPhoneNumberValid());
    }
}

