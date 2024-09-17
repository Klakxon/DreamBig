package com.example.DreamBig.model;

public class Subscription {
    private Long id;
    private String subscriptionType; // Наприклад, "Monthly", "Yearly", "One-time"
    private boolean isActive;
    private User user;

    public Subscription(Long id, String subscriptionType, boolean isActive, User user) {
        if (subscriptionType == null || subscriptionType.isEmpty()) {
            throw new IllegalArgumentException("Subscription type cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.id = id;
        this.subscriptionType = subscriptionType;
        this.isActive = isActive;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        if (subscriptionType == null || subscriptionType.isEmpty()) {
            throw new IllegalArgumentException("Subscription type cannot be empty");
        }
        this.subscriptionType = subscriptionType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.user = user;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public boolean isValidFor(String sessionType) {
        // Простий приклад: перевіряємо, чи відповідає тип абонемента типу тренування
        return this.subscriptionType.equalsIgnoreCase(sessionType) || "Unlimited".equalsIgnoreCase(subscriptionType);
    }
}