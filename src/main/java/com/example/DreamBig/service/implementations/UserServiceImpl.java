package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneVerificationService phoneVerificationService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity createUser(UserEntity user) {
        try {
            boolean isPhoneValid = phoneVerificationService.verifyPhoneNumber(user.getPhoneNumber());
            user.setPhoneNumberValid(isPhoneValid);

            if (isPhoneValid) {
                logger.info("User created with valid phone number: {}", user.getPhoneNumber());
            } else {
                logger.warn("User created with invalid phone number: {}", user.getPhoneNumber());
            }

        } catch (Exception e) {
            logger.error("Failed to verify phone number for user: {}", user.getPhoneNumber(), e);
        }
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
