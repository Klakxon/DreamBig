package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.User;

public interface ValidationService {
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidEmail(String email);
    boolean checkPassword(User user, String password);
}
