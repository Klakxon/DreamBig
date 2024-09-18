package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Subscription;

public interface SubscriptionService {
    void activate(Subscription subscription);
    void deactivate(Subscription subscription);
    boolean isValidFor(Subscription subscription, String sessionType);
}
