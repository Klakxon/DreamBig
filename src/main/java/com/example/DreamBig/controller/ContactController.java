package com.example.DreamBig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("currentPage", "contact");
        return "contact";
    }
}
