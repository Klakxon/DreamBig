package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.User;

public interface ValidationService {
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidEmail(String email);
    boolean checkPassword(User user, String password);
}
