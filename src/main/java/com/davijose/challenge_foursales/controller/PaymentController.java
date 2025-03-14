package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.dto.PaymentRequest;
import com.davijose.challenge_foursales.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<String> processPayment(@PathVariable BigInteger orderId, @RequestBody PaymentRequest paymentRequest) {
        paymentService.processPayment(orderId, paymentRequest);
        return ResponseEntity.ok("Payment processed successfully.");
    }
}