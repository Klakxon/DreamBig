package com.example.DreamBig.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clubs")
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private boolean hasPool;

    private boolean hasGym;

    private boolean hasCardioZone;


    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<TrainerEntity> trainers;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<SessionEntity> sessions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean isHasGym() {
        return hasGym;
    }

    public void setHasGym(boolean hasGym) {
        this.hasGym = hasGym;
    }

    public boolean isHasCardioZone() {
        return hasCardioZone;
    }

    public void setHasCardioZone(boolean hasCardioZone) {
        this.hasCardioZone = hasCardioZone;
    }

    public List<TrainerEntity> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TrainerEntity> trainers) {
        this.trainers = trainers;
    }

    public List<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
    }
}