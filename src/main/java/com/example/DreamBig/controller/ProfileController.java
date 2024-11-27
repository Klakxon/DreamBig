package com.example.DreamBig.controller;

import com.example.DreamBig.entity.User;
import com.example.DreamBig.repository.UserRepository;
import com.example.DreamBig.service.interfaces.ValidationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    private static final String PROFILE_PAGE = "profile";

    private final ValidationService validationService;
    private final UserRepository userRepository;


    public ProfileController(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }


    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        String email = currentUser.getUsername();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        model.addAttribute("email", user.getEmail());
        model.addAttribute("fullName", user.getFullName());
        return PROFILE_PAGE;
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("fullName") String fullName,
                                Model model,
                                @AuthenticationPrincipal UserDetails currentUser) {

        if (!validationService.isValidEmail(email) || fullName == null || fullName.isEmpty()) {
            model.addAttribute("error", "Invalid data provided");
            return PROFILE_PAGE;
        }

        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setEmail(email);
        user.setFullName(fullName);
        userRepository.save(user);

        model.addAttribute("email", user.getEmail());
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("successMessage", "Profile updated successfully!");
        return PROFILE_PAGE;
    }
}
