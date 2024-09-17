package com.example.DreamBig.model;

public class FeedbackRequest {
    private Long id;
    private String phoneNumber;
    private String message;
    private String status; // "New", "Processed", "Replied"


    public FeedbackRequest(Long id, String phoneNumber, String message, String status) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
