package com.example.DreamBig.model;

public class Subscription {
    private Long id;
    private String subscriptionType; // "MONTHLY", "YEARLY", "ONE-TIME"
    private boolean isActive;
    private User user;


    public Subscription(Long id, String subscriptionType, boolean isActive, User user) {
        this.id = id;
        this.subscriptionType = subscriptionType;
        this.isActive = isActive;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(String subscriptionType) { this.subscriptionType = subscriptionType; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}