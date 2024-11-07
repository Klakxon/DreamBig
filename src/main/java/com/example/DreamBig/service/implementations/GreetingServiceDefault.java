package com.example.DreamBig.service.implementations;

import com.example.DreamBig.service.interfaces.GreetingService;

public class GreetingServiceDefault implements GreetingService {
      @Override
    public String greet() {
        return "Welcome to DreamBig sport club application!";
    }
}
