package com.example.DreamBig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("fullName") String fullName,
                                Model model) {
        model.addAttribute("email", email);
        model.addAttribute("fullName", fullName);
        return "profile";
    }
}