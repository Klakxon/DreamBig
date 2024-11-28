package com.example.DreamBig.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @GetMapping("/service")
    public String servicePage(Model model) {
        logger.debug("Request for /service received");

        model.addAttribute("currentPage", "service");
        logger.debug("Model attribute added: currentPage = service");

        return "service";
    }
}
