package com.example.DreamBig.dto;

public class PaymentDTO {
    private Long id;
    private Double amount;
    private String status;
    private Long userId;
    private String date;

    // Конструктори, геттери та сеттери
    public PaymentDTO() {}

    public PaymentDTO(Long id, Double amount, String status, Long userId, String date) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
