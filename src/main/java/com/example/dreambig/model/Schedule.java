package com.example.dreambig.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Розклад занять
 */
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
}