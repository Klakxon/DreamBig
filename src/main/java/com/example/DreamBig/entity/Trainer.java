package com.example.DreamBig.entity;

import com.example.DreamBig.enums.Roles;
import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue("TRAINER")
public class Trainer extends User {

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Session> schedule;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Trainer() {
        super();
    }

    public Trainer(Long id, String fullName, String phoneNumber, String email, String password, List<Session> schedule, Club club) {
        super(id, fullName, phoneNumber, email, password, Roles.TRAINER.toString());
        this.schedule = schedule != null ? schedule : new java.util.ArrayList<>();
        this.club = club;
    }

    public List<Session> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Session> schedule) {
        this.schedule = schedule;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
