package com.example.DreamBig.tasks;

import com.example.DreamBig.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanupTask {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public CleanupTask(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Scheduled(fixedRate = 86400000)
    public void cleanUpInactiveTemporarySubscriptions() {
        int deletedSubscriptions = subscriptionRepository.deleteInactiveTemporarySubscriptions();
        System.out.println("Очищено " + deletedSubscriptions + " неактивних тимчасових підписок.");
    }
}