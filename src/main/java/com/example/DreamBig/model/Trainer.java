package com.example.DreamBig.model;

import java.util.List;

public class Trainer extends User {
    private List<Session> schedule; // Розклад тренувань тренера

    public Trainer(Long id, String fullName, String phoneNumber, String email, String password, String role, List<Session> schedule) {
        super(id, fullName, phoneNumber, email, password, role);
        if (!isTrainerRole(role)) {
            throw new IllegalArgumentException("Role must be 'TRAINER' for this class");
        }
        this.schedule = schedule;
    }

    public List<Session> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Session> schedule) {
        this.schedule = schedule;
    }

    public boolean isAvailable(String dateTime) {
        if (schedule == null || schedule.isEmpty()) {
            return true;
        }
        for (Session session : schedule) {
            if (session.getDateTime().equals(dateTime)) {
                return false; // Тренер зайнятий у цей час
            }
        }
        return true; // Тренер вільний
    }

    public void bookSession(User user, String dateTime, String sessionType) {
        if (!isAvailable(dateTime)) {
            throw new IllegalStateException("Trainer is not available at this time");
        }
        Session newSession = new Session(null, sessionType, dateTime, this, List.of(user));
        schedule.add(newSession);
    }

    private boolean isTrainerRole(String role) {
        return "TRAINER".equalsIgnoreCase(role);
    }

    public void cancelSession(String dateTime) {
        if (schedule != null && !schedule.isEmpty()) {
            schedule.removeIf(session -> session.getDateTime().equals(dateTime));
        }
    }

    public boolean hasSessionAt(String dateTime) {
        if (schedule == null || schedule.isEmpty()) {
            return false;
        }
        for (Session session : schedule) {
            if (session.getDateTime().equals(dateTime)) {
                return true; // У тренера є сесія у вказаний час
            }
        }
        return false;
    }
}