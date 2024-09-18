package com.example.DreamBig.service.implementations;

import com.example.DreamBig.model.Payment;
import com.example.DreamBig.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    public void markAsCompleted(Payment payment) {
        payment.setStatus("Completed");
    }

    public boolean isCompleted(Payment payment) {
        return "Completed".equalsIgnoreCase(payment.getStatus());
    }

    public boolean isPending(Payment payment) {
        return "Pending".equalsIgnoreCase(payment.getStatus());
    }
}
