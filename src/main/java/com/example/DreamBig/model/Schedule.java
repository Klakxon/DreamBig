package com.example.DreamBig.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private Long id;
    private String date;
    private List<Session> sessions;

    public Schedule(Long id, String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        this.id = id;
        this.date = date;
        this.sessions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }
        this.date = date;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        if (!session.getDateTime().contains(date)) {
            throw new IllegalArgumentException("Session date does not match the schedule date");
        }
        sessions.add(session);
    }

    public void removeSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }
        sessions.remove(session);
    }

    public List<Session> getSessionsByTrainer(Trainer trainer) {
        List<Session> trainerSessions = new ArrayList<>();
        for (Session session : sessions) {
            if (session.getTrainer().equals(trainer)) {
                trainerSessions.add(session);
            }
        }
        return trainerSessions;
    }

    public boolean hasSession(Session session) {
        return sessions.contains(session);
    }

    public boolean isFullyBooked() {
        // You could add logic here to determine if all available session spots are booked
        for (Session session : sessions) {
            if (session.getParticipants().size() < 10) { // Example: a session can have up to 10 participants
                return false;
            }
        }
        return true;
    }
}