package com.example.DreamBig.controller;

import com.example.DreamBig.entity.User;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private static final String PROFILE_PAGE = "profile";
    private static final String ERROR_PARAMETER = "error";
    private static final int MIN_PASSWORD_LENGTH = 4;

    private final ValidationService validationService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public ProfileController(UserRepository userRepository, ValidationService validationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено"));

        addParameters(model, user);

        return PROFILE_PAGE;
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("fullName") String fullName,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("password") String password,
                                Model model,
                                @AuthenticationPrincipal UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Користувача не знайдено"));
        addParameters(model, user);

        if (!validationService.isValidEmail(email) || fullName == null || fullName.isEmpty()) {
            model.addAttribute(ERROR_PARAMETER, "Некоректні дані");
            return PROFILE_PAGE;
        }

        if (password != null && !password.isEmpty() && password.length() < MIN_PASSWORD_LENGTH) {
            model.addAttribute(ERROR_PARAMETER, "Пароль має містити не менше " + MIN_PASSWORD_LENGTH + " символів.");
            return PROFILE_PAGE;
        }

        user.setEmail(email);
        user.setFullName(fullName);
        if (validationService.isValidPhoneNumber(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        } else {
            model.addAttribute(ERROR_PARAMETER, "Номер телефону не коректний");
            return PROFILE_PAGE;
        }
        if (password != null) {
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        }
        userRepository.save(user);

        addParameters(model, user);
        model.addAttribute("successMessage", "Профіль успішно оновлено!");

        return PROFILE_PAGE;
    }

    private void addParameters(Model model, User user) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("phoneNumber", user.getPhoneNumber());

        model.addAttribute("subscriptions", user.getSubscriptions());
        model.addAttribute("payments", user.getPayments());
        model.addAttribute("sessions", user.getSessions());
    }
}
