package com.example.DreamBig.model;

import java.util.List;

public class Session {
    private Long id;
    private String sessionType; // "BIG GROUP", "SMALL GROUP", "INDIVIDUAL"
    private String dateTime;
    private Trainer trainer;
    private List<User> participants; // Учасники тренування


    public Session(Long id, String sessionType, String dateTime, Trainer trainer, List<User> participants) {
        this.id = id;
        this.sessionType = sessionType;
        this.dateTime = dateTime;
        this.trainer = trainer;
        this.participants = participants;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSessionType() { return sessionType; }
    public void setSessionType(String sessionType) { this.sessionType = sessionType; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    public List<User> getParticipants() { return participants; }
    public void setParticipants(List<User> participants) { this.participants = participants; }
}