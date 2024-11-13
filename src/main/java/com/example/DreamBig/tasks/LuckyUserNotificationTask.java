package com.example.DreamBig.tasks;

import com.example.DreamBig.service.implementations.LuckyUserNotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LuckyUserNotificationTask {

    private final LuckyUserNotificationService notificationService;

    public LuckyUserNotificationTask(LuckyUserNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void notifyLuckyUser() {
        notificationService.sendLuckyUserNotification();
    }
}
