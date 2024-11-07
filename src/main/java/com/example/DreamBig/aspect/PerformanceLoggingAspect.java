package com.example.DreamBig.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceLoggingAspect.class);

    @Pointcut("execution(* com.example.DreamBig.service..*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        logger.info("Method {} executed in {} ms", joinPoint.getSignature(), duration / 1_000_000);
        return result;
    }
}
