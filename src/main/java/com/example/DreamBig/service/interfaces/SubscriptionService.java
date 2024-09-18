package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.Subscription;

public interface SubscriptionService {
    void activate(Subscription subscription);
    void deactivate(Subscription subscription);
    boolean isValidFor(Subscription subscription, String sessionType);
}
