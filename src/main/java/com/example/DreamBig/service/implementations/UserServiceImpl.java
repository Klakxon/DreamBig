package com.example.DreamBig.service.implementations;

import com.example.DreamBig.enums.Roles;
import com.example.DreamBig.model.User;
import com.example.DreamBig.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public boolean hasRole(User user, String role) {
        return user.getRole().equalsIgnoreCase(role);
    }

    public void assignRole(User user, String role) {
        if (role != null && !role.isEmpty()) {
            user.setRole(role);
        } else {
            throw new IllegalArgumentException("Role cannot be empty");
        }
    }

    public boolean isAdmin(User user) {
        return Roles.ADMIN.toString().equalsIgnoreCase(user.getRole());
    }

    public boolean isTrainer(User user) {
        return Roles.TRAINER.toString().equalsIgnoreCase(user.getRole());
    }

    public boolean isUser(User user) {
        return Roles.USER.toString().equalsIgnoreCase(user.getRole());
    }
}
