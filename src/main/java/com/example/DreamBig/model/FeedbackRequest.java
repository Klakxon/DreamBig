package com.example.DreamBig.model;

public class FeedbackRequest {
    private Long id;
    private String phoneNumber;
    private String message;
    private String status; // Наприклад, "New", "Processed"

    public FeedbackRequest(Long id, String phoneNumber, String message, String status) {
        if (phoneNumber == null || !isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }

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
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be empty");
        }
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }
        this.status = status;
    }

    public boolean isProcessed() {
        return "Processed".equalsIgnoreCase(status);
    }

    public void markAsProcessed() {
        this.status = "Processed";
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "\\+?\\d{10,13}";
        return phoneNumber.matches(phonePattern);
    }
}
