package com.example.DreamBig.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionType;

    private String dateTime;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToMany
    @JoinTable(
            name = "session_participants",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    public Session() {}

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

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}

