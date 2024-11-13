package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.SubscriptionEntity;
import com.example.DreamBig.repository.SubscriptionRepository;
import com.example.DreamBig.service.interfaces.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    @Cacheable(value = "subscriptions", key = "#id")
    public SubscriptionEntity getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "subscriptions", key = "'allSubscriptions'")
    public List<SubscriptionEntity> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    @CachePut(value = "subscriptions", key = "#subscription.id")
    public SubscriptionEntity createSubscription(SubscriptionEntity subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    @CachePut(value = "subscriptions", key = "#subscription.id")
    public SubscriptionEntity updateSubscription(SubscriptionEntity subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    @CacheEvict(value = "subscriptions", key = "#id")
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
