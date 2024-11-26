package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.Payment;
import java.util.List;

public interface PaymentService {
    Payment createPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getAllPayments();
    Payment updatePayment(Payment payment);
    void deletePayment(Long id);
}
