package com.example.DreamBig.entity;

import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
import jakarta.persistence.*;

@Entity
@Table(name = "feedback_requests")
public class FeedbackRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    private String message;

    private String status;

    @Transient
    private final ValidationService validationService = new ValidationServiceImpl();;

    public FeedbackRequest() {}

    public FeedbackRequest(Long id, String phoneNumber, String message, String status) {
        if (phoneNumber == null || !validationService.isValidPhoneNumber(phoneNumber)) {
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
        if (!validationService.isValidPhoneNumber(phoneNumber)) {
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
}
