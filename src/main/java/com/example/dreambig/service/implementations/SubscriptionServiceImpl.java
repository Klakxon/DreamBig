package com.example.dreambig.service.implementations;

import com.example.dreambig.model.Subscription;
import com.example.dreambig.service.interfaces.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    public void activate(Subscription subscription) {
        subscription.setActive(true);
    }

    public void deactivate(Subscription subscription) {
        subscription.setActive(false);
    }

    public boolean isValidFor(Subscription subscription, String sessionType) {
        // Простий приклад: перевіряємо, чи відповідає тип абонемента типу тренування
        String type = subscription.getType();
        return type.equalsIgnoreCase(sessionType) || "Unlimited".equalsIgnoreCase(type);
    }
}
