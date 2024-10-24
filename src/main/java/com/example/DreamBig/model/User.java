package com.example.DreamBig.model;

import com.example.DreamBig.config.RolePrivilegeConfig;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;

import java.util.HashSet;
import java.util.Set;

/**
 * Батьківський клас, який описує звичайного користувача.
 */
public class User {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private Set<String> privileges;
    private boolean isPhoneNumberValid;

    private ValidationService validationService;

    public User(Long id, String fullName, String phoneNumber, String email, String password, String role) {
        if (!validationService.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (!validationService.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.privileges = new HashSet<>(getPrivilegesByRole(role));

        this.validationService = new ValidationServiceImpl();
    }

    private Set<String> getPrivilegesByRole(String role) {
        switch (role.toUpperCase()) {
            case "USER":
                return RolePrivilegeConfig.getUserPrivileges();
            case "TRAINER":
                return RolePrivilegeConfig.getTrainerPrivileges();
            case "ADMIN":
                return RolePrivilegeConfig.getAdminPrivileges();
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!validationService.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!validationService.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        this.role = role;
    }

    public boolean isPhoneNumberValid() {
        return isPhoneNumberValid;
    }

    public void setPhoneNumberValid(boolean isPhoneNumberValid) {
        this.isPhoneNumberValid = isPhoneNumberValid;
    }
}