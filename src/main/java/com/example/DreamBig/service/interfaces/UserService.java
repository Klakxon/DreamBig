package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.UserEntity;
import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity user) throws Exception;
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers();
    UserEntity updateUser(UserEntity user);
    void deleteUser(Long id);

    boolean hasRole(UserEntity user, String role);
    void assignRole(UserEntity user, String role);
    boolean isAdmin(UserEntity user);
    boolean isTrainer(UserEntity user);
    boolean isUser(UserEntity user);
}
