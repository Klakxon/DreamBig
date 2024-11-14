package com.example.DreamBig.aspect;

import com.example.DreamBig.model.CustomUserDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitAspect {

    private static final long TIME_WINDOW = TimeUnit.MINUTES.toMillis(1);
    private final ConcurrentHashMap<String, Long> userRequestCount = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> userLastRequestTime = new ConcurrentHashMap<>();

    @Pointcut("execution(* com.example.DreamBig.service..*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object rateLimit(ProceedingJoinPoint joinPoint) throws Throwable {
        String userId = getUserIdFromContext();
        long currentTime = System.currentTimeMillis();
        Long lastRequestTime = userLastRequestTime.get(userId);

        if (lastRequestTime != null && currentTime - lastRequestTime < TIME_WINDOW) {
            long requestCount = userRequestCount.getOrDefault(userId, 0L);
            if (requestCount >= 10) {
                throw new IllegalStateException("Too many requests");
            }
            userRequestCount.put(userId, requestCount + 1);
        } else {
            userRequestCount.put(userId, 1L);
            userLastRequestTime.put(userId, currentTime);
        }

        return joinPoint.proceed();
    }

    private String getUserIdFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                if (userDetails instanceof CustomUserDetails) {
                    CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
                    return customUserDetails.getUserId().toString();
                }
                return userDetails.getUsername();
            }
        }
        throw new IllegalStateException("User is not authenticated");
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void clean() {
        long currentTime = System.currentTimeMillis();
        userLastRequestTime.keySet().removeIf(userId ->
                currentTime - userLastRequestTime.get(userId) > TIME_WINDOW
        );
        userRequestCount.keySet().removeIf(userId ->
                !userLastRequestTime.containsKey(userId)
        );
    }
}