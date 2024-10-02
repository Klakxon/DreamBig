package com.example.DreamBig.controller;

import com.example.DreamBig.dto.PaymentDTO;
import com.example.DreamBig.exception.InvalidInputException;
import com.example.DreamBig.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private List<PaymentDTO> payments = new ArrayList<>();

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return payments;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        return payments.stream()
                .filter(payment -> payment.getId().equals(id))
                .findFirst()
                .map(payment -> new ResponseEntity<>(payment, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        if (paymentDTO.getAmount() == null || paymentDTO.getAmount() <= 0) {
            throw new InvalidInputException("Amount must be positive");
        }
        if (paymentDTO.getStatus() == null || paymentDTO.getStatus().isEmpty()) {
            throw new InvalidInputException("Status cannot be empty");
        }
        payments.add(paymentDTO);
        return new ResponseEntity<>(paymentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO updatedPayment) {
        for (PaymentDTO payment : payments) {
            if (payment.getId().equals(id)) {
                payment.setAmount(updatedPayment.getAmount());
                payment.setStatus(updatedPayment.getStatus());
                payment.setUserId(updatedPayment.getUserId());
                payment.setDate(updatedPayment.getDate());
                return new ResponseEntity<>(payment, HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("Payment not found with ID: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        boolean removed = payments.removeIf(payment -> payment.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>("Payment with ID " + id + " deleted.", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Payment not found with ID: " + id);
        }
    }
}
