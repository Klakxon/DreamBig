package com.example.DreamBig.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String status = "Pending";

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public void markAsCompleted() {
        this.status = "Completed";
    }

    public boolean isCompleted() {
        return "Completed".equalsIgnoreCase(this.status);
    }

    public boolean isPending() {
        return "Pending".equalsIgnoreCase(this.status);
    }
}
