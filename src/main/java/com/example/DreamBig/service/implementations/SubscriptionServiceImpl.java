package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.Subscription;
import com.example.DreamBig.service.interfaces.SubscriptionService;
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

        String type = subscription.getType();
        return type.equalsIgnoreCase(sessionType) || "Unlimited".equalsIgnoreCase(type);
    }
}
