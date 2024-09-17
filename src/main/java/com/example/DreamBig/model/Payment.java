package com.example.DreamBig.model;

public class Payment {
    private Long id;
    private Double amount;
    private String status; // "Pending", "Completed"
    private User user;
    private String date;


    public Payment(Long id, Double amount, String status, User user, String date) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.date = date;
    }

    // Getters і Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}