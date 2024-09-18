package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.User;

public interface UserService {
    boolean hasRole(User user, String role);
    void assignRole(User user, String role);
    boolean isAdmin(User user);
    boolean isTrainer(User user);
    boolean isUser(User user);
}
