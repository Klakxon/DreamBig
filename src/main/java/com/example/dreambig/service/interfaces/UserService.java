package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.User;

public interface UserService {
    boolean hasRole(User user, String role);
    void assignRole(User user, String role);
    boolean isAdmin(User user);
    boolean isTrainer(User user);
    boolean isUser(User user);
}
