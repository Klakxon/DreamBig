package com.example.DreamBig.dto;

import jakarta.validation.constraints.*;

public class UserDTO {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be a positive number")
    private Long id;

    @NotEmpty(message = "Full name cannot be empty")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+?\\d{10,13}", message = "Phone number must be between 10 and 13 digits and can optionally start with a plus")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotEmpty(message = "Role cannot be empty")
    private String role;

    @NotNull(message = "IsPhoneNumberValid cannot be null")
    private boolean isPhoneNumberValid;

    public UserDTO() {}

    public UserDTO(Long id, String fullName, String phoneNumber, String email, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
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
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isPhoneNumberValid() {
        return isPhoneNumberValid;
    }

    public void setPhoneNumberValid(boolean isPhoneNumberValid) {
        this.isPhoneNumberValid = isPhoneNumberValid;
    }
}
