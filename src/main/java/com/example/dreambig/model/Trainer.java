package com.example.dreambig.model;

import com.example.dreambig.enums.Roles;

import java.util.List;

/**
 * Клас, який описує тренера в спортивному клубі.
 */
public class Trainer extends User {
    private List<Session> schedule; // Розклад тренувань тренера

    public Trainer(Long id, String fullName, String phoneNumber, String email, String password, List<Session> schedule) {
        super(id, fullName, phoneNumber, email, password, Roles.TRAINER.toString());
        this.schedule = schedule != null ? schedule : new java.util.ArrayList<>();
    }

    public List<Session> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Session> schedule) {
        this.schedule = schedule;
    }
}
