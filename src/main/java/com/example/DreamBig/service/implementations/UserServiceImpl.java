package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean hasRole(UserEntity user, String role) {
        return user.getRole().equalsIgnoreCase(role);
    }

    @Override
    public void assignRole(UserEntity user, String role) {
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public boolean isAdmin(UserEntity user) {
        return hasRole(user, "ADMIN");
    }

    @Override
    public boolean isTrainer(UserEntity user) {
        return hasRole(user, "TRAINER");
    }

    @Override
    public boolean isUser(UserEntity user) {
        return hasRole(user, "USER");
    }
}
