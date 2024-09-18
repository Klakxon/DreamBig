package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.model.Payment;

public interface PaymentService {
    void markAsCompleted(Payment payment);
    boolean isCompleted(Payment payment);
    boolean isPending(Payment payment);
}
