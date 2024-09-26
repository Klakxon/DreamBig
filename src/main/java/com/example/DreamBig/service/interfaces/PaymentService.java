package com.example.DreamBig.service.interfaces;

import com.example.DreamBig.entity.PaymentEntity;
import java.util.List;

public interface PaymentService {
    PaymentEntity createPayment(PaymentEntity payment);
    PaymentEntity getPaymentById(Long id);
    List<PaymentEntity> getAllPayments();
    PaymentEntity updatePayment(PaymentEntity payment);
    void deletePayment(Long id);
}
