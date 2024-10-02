package com.example.DreamBig.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentDTO {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be a positive number")
    private Long id;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    @NotEmpty(message = "Amount cannot be empty")
    private String status;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @NotEmpty(message = "Date cannot be empty")
    private String date;

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
