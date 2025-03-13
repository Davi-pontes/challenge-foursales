package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.controller.dto.OrderItemRequest;
import com.davijose.challenge_foursales.controller.dto.OrderRequest;
import com.davijose.challenge_foursales.controller.dto.OrderResponse;
import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.domain.orderItem.OrderItem;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.repositories.OrderRepository;
import com.davijose.challenge_foursales.repositories.ProductRepository;
import com.davijose.challenge_foursales.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public Page<OrderResponse> findByUserId(UUID userId, Pageable pagination){
        Page<Order> ordersPages = orderRepository.findByUserId(userId, pagination);

        return ordersPages.map(OrderResponse::new);
    }
    public Order findById(UUID id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        return optionalOrder.orElse(null);
    }
    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = new Order();
        order.setStatus(Status.WAITING_PAYMENT);

        User user = userRepository.findById(orderRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        float totalOrder = 0.0f;

        for (OrderItemRequest itemRequest : orderRequest.orderItems()) {
            productService.validateStock(itemRequest.productId());

            Product product = productService.findById(itemRequest.productId());

            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());

            totalOrder += productPrice.floatValue();

            OrderItem orderItem = new OrderItem(product);
            order.addOrderItem(orderItem);
        }
        System.out.println(totalOrder);

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(savedOrder);
    }
    public Order update(UUID id, Order order){
        Order orderFound = findById(id);

        if(orderFound != null){
            return orderRepository.save(order);
        }else{
            return order;
        }
    }
}
