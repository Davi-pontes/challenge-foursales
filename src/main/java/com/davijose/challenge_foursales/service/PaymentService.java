package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.controller.dto.PaymentRequest;
import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Transactional
    public Order processPayment(BigInteger orderId, PaymentRequest paymentRequest){
        Order order = orderService.findById(orderId);

        if (!order.getStatus().equals(Status.PENDING)) {
            throw new RuntimeException("Order is not in a valid state for payment.");
        }

        if (!processExternalPayment(paymentRequest)) {
            throw new RuntimeException("Payment processing failed.");
        }

        order.getOrderItems().forEach(item -> {
            productService.decrementStock(item.getProduct().getId(), 1);
        });
        order.setStatus(Status.APPROVED);

        return orderRepository.save(order);
    }
    private boolean processExternalPayment(PaymentRequest paymentRequest) {
        return true;
    }
}
