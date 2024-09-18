package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.User;

public interface ValidationService {
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidEmail(String email);
    boolean checkPassword(User user, String password);
}
