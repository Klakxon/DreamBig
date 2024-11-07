package com.example.DreamBig.service.implementations;

import com.example.DreamBig.service.interfaces.GreetingService;
import org.springframework.beans.factory.annotation.Value;

public class GreetingServiceDefault implements GreetingService {
      @Override
    public String greet() {
        return "Welcome to DreamBig sport club application!";
    }
}
