package com.example.DreamBig.service.implementations;

import com.example.DreamBig.entity.Payment;
import com.example.DreamBig.repository.PaymentRepository;
import com.example.DreamBig.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Cacheable(value = "payments", key = "#id")
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    @Cacheable(value = "payments", key = "'allPayments'")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    @CachePut(value = "payments", key = "#payment.id")
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @CachePut(value = "payments", key = "#payment.id")
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    @CacheEvict(value = "payments", key = "#id")
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}