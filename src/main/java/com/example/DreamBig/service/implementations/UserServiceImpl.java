package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.UserEntity;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @CachePut(value = "users", key = "#user.id")
    public UserEntity createUser(UserEntity user) throws Exception {
        boolean isPhoneValid = phoneVerificationService.verifyPhoneNumber(user.getPhoneNumber());
        user.setPhoneNumberValid(isPhoneValid);

        if (isPhoneValid) {
            logger.info("User created with valid phone number: {}", user.getPhoneNumber());
        } else {
            logger.warn("User created with invalid phone number: {}", user.getPhoneNumber());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "users", key = "'allUsers'")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @CachePut(value = "users", key = "#user.id")
    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
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

    private String getUserIdFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();
            }
        }
        throw new IllegalStateException("User is not authenticated");
    }
}
