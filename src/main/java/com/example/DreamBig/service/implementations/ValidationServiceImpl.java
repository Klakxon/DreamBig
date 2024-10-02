package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.User;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "\\+?\\d{10,13}";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
