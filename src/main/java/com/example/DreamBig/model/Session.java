package com.example.DreamBig.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private String sessionType; // Наприклад, "Group" або "Individual"
    private String dateTime;
    private Trainer trainer;
    private List<User> participants;

    public Session(Long id, String sessionType, String dateTime, Trainer trainer) {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new IllegalArgumentException("Session type cannot be empty");
        }
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException("Date and time cannot be empty");
        }
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer cannot be null");
        }

        this.id = id;
        this.sessionType = sessionType;
        this.dateTime = dateTime;
        this.trainer = trainer;
        this.participants = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        if (sessionType == null || sessionType.isEmpty()) {
            throw new IllegalArgumentException("Session type cannot be empty");
        }
        this.sessionType = sessionType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException("Date and time cannot be empty");
        }
        this.dateTime = dateTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer cannot be null");
        }
        this.trainer = trainer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (participants.size() >= getMaxParticipants()) {
            throw new IllegalStateException("Session is fully booked");
        }
        participants.add(user);
    }

    public void removeParticipant(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        participants.remove(user);
    }

    public boolean isFullyBooked() {
        return participants.size() >= getMaxParticipants();
    }

    private int getMaxParticipants() {
        return "Group".equalsIgnoreCase(sessionType) ? 10 : 1; // Групові сесії до 10 людей, індивідуальні - 1
    }

    public boolean isParticipant(User user) {
        return participants.contains(user);
    }
}