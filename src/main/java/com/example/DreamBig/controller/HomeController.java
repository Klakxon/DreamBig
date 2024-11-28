package com.example.DreamBig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String defaultPage(Model model) {
        model.addAttribute("currentPage", "home");
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("currentPage", "home");
        return "home";
    }
}
