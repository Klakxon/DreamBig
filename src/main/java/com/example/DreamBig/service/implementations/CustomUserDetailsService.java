package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.User;
import com.example.DreamBig.model.CustomUserDetails;
import com.example.DreamBig.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Пошук користувача за email
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Повертаємо CustomUserDetails з паролем
        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRole(), user.getPrivileges());
    }
}

