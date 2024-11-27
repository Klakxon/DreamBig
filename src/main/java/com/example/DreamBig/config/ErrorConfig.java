package com.example.DreamBig.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class ErrorConfig implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpServletResponse.SC_FORBIDDEN) {
                return "error/403";
            } else if (statusCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
                return "error/500";
            }
        }
        return "error/general";
    }
}
