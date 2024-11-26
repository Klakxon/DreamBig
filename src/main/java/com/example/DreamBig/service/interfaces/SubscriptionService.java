package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Subscription;
import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(Long id);
    List<Subscription> getAllSubscriptions();
    Subscription updateSubscription(Subscription subscription);
    void deleteSubscription(Long id);
}
