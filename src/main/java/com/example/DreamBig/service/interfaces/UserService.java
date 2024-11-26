package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.User;
import java.util.List;

public interface UserService {
    User createUser(User user) throws Exception;
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long id);

    boolean hasRole(User user, String role);
    void assignRole(User user, String role);
    boolean isAdmin(User user);
    boolean isTrainer(User user);
    boolean isUser(User user);
}
