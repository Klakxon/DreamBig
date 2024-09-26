package com.example.DreamBig.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainers")
public class TrainerEntity extends UserEntity {

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<SessionEntity> schedule;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private ClubEntity club;

    public List<SessionEntity> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<SessionEntity> schedule) {
        this.schedule = schedule;
    }

    public ClubEntity getClub() {
        return club;
    }

    public void setClub(ClubEntity club) {
        this.club = club;
    }
}

