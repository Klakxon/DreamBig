package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.SubscriptionEntity;
import com.example.DreamBig.repository.SubscriptionRepository;
import com.example.DreamBig.service.interfaces.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity createSubscription(SubscriptionEntity subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public SubscriptionEntity getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubscriptionEntity> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public SubscriptionEntity updateSubscription(SubscriptionEntity subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
