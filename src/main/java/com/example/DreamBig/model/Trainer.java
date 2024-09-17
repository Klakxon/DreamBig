package com.example.DreamBig.model;

import java.util.List;

public class Trainer extends User {
    private List<Session> schedule;

    public Trainer(Long id, String fullName, String phoneNumber, String email, String password, String role, List<Session> schedule) {
        super(id, fullName, phoneNumber, email, password, role);
        this.schedule = schedule;
    }

    // Перевірка доступності тренера на конкретний час
    public boolean isAvailable(String dateTime) {
        // Логіка для перевірки доступності
        return true;
    }


    public List<Session> getSchedule() { return schedule; }
    public void setSchedule(List<Session> schedule) { this.schedule = schedule; }
}