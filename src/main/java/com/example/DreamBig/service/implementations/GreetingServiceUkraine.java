package com.example.DreamBig.service.implementations;

import com.example.DreamBig.service.interfaces.GreetingService;

public class GreetingServiceUkraine implements GreetingService {
    @Override
    public String greet() {
        return "Слава Україні! Ласкаво просимо у додаток мережі спортклубів DreamBig!";
    }
}
