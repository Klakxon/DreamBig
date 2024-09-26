package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.SubscriptionEntity;
import java.util.List;

public interface SubscriptionService {
    SubscriptionEntity createSubscription(SubscriptionEntity subscription);
    SubscriptionEntity getSubscriptionById(Long id);
    List<SubscriptionEntity> getAllSubscriptions();
    SubscriptionEntity updateSubscription(SubscriptionEntity subscription);
    void deleteSubscription(Long id);
}
