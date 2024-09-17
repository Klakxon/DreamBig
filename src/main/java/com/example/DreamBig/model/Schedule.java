package com.example.DreamBig.model;

import java.util.List;

public class Schedule {
    private Long id;
    private String date;
    private List<Session> sessions;

    public Schedule(Long id, String date, List<Session> sessions) {
        this.id = id;
        this.date = date;
        this.sessions = sessions;
    }

    // Getters і Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public List<Session> getSessions() { return sessions; }
    public void setSessions(List<Session> sessions) { this.sessions = sessions; }
}