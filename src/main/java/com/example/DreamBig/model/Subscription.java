package com.example.DreamBig.model;

/**
 * Абонемент
 */
public class Subscription {
    private Long id;
    private String type;
    private boolean isActive;
    private User user;

    public Subscription(Long id, String type, boolean isActive, User user) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Subscription type cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.id = id;
        this.type = type;
        this.isActive = isActive;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Subscription type cannot be empty");
        }
        this.type = type;
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
}