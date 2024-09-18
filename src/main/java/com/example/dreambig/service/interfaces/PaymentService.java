package com.example.dreambig.service.interfaces;

import com.example.dreambig.model.Payment;

public interface PaymentService {
    void markAsCompleted(Payment payment);
    boolean isCompleted(Payment payment);
    boolean isPending(Payment payment);
}
