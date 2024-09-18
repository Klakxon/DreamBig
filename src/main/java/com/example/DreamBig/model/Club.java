package com.example.DreamBig.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Спортивний клуб
 */
public class Club {
    private Long id;
    private String name;
    private String address;
    private boolean hasPool;
    private boolean hasGym;
    private boolean hasCardioZone;
    private List<Trainer> trainers;
    private List<Session> sessions;

    public Club(Long id, String name, String address, boolean hasPool, boolean hasGym, boolean hasCardioZone) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Club name cannot be empty");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Club address cannot be empty");
        }
        this.id = id;
        this.name = name;
        this.address = address;
        this.hasPool = hasPool;
        this.hasGym = hasGym;
        this.hasCardioZone = hasCardioZone;
        this.trainers = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Club name cannot be empty");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Club address cannot be empty");
        }
        this.address = address;
    }

    public boolean hasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    public boolean hasGym() {
        return hasGym;
    }

    public void setHasGym(boolean hasGym) {
        this.hasGym = hasGym;
    }

    public boolean hasCardioZone() {
        return hasCardioZone;
    }

    public void setHasCardioZone(boolean hasCardioZone) {
        this.hasCardioZone = hasCardioZone;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
