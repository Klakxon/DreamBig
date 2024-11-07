package com.example.DreamBig.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @Pointcut("execution(* com.example.DreamBig.service..*(..))")
    public void serviceMethods() {}

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void handleException(Exception ex) {
        logger.error("Exception occurred: " + ex.getMessage(), ex);
    }

    @AfterThrowing(value = "execution(* com.example.DreamBig.controller.*(..))", throwing = "ex")
    public void logControllerException(Exception ex) {
        logger.error("Exception caught in controller layer: {}", ex.getMessage(), ex);
    }
}
