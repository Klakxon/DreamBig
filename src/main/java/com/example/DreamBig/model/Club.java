package com.example.DreamBig.model;

public class Club {
    private Long id;
    private String name;
    private String address;
    private boolean hasPool;
    private boolean hasGym;
    private boolean hasCardioZone;

    public Club(Long id, String name, String address, boolean hasPool, boolean hasGym, boolean hasCardioZone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hasPool = hasPool;
        this.hasGym = hasGym;
        this.hasCardioZone = hasCardioZone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isHasPool() { return hasPool; }
    public void setHasPool(boolean hasPool) { this.hasPool = hasPool; }

    public boolean isHasGym() { return hasGym; }
    public void setHasGym(boolean hasGym) { this.hasGym = hasGym; }

    public boolean isHasCardioZone() { return hasCardioZone; }
    public void setHasCardioZone(boolean hasCardioZone) { this.hasCardioZone = hasCardioZone; }
}
