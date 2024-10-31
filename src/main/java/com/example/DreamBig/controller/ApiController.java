package com.example.DreamBig.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PreAuthorize("hasAuthority('visitSession')")
    @GetMapping("/session/visit")
    public String visitSession() {
        return "Access to visit session";
    }

    @PreAuthorize("hasAuthority('arrangeSession')")
    @GetMapping("/session/arrange")
    public String arrangeSession() {
        return "Access to arrange session";
    }

    @PreAuthorize("hasAuthority('accessSecretData')")
    @GetMapping("/secret")
    public String accessSecretData() {
        return "Access to secret data";
    }
}