package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.domain.orderItem.OrderItem;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.dto.OrderItemRequest;
import com.davijose.challenge_foursales.dto.OrderRequest;
import com.davijose.challenge_foursales.dto.OrderResponse;
import com.davijose.challenge_foursales.dto.UserCountResponse;
import com.davijose.challenge_foursales.error.InsufficientStockException;
import com.davijose.challenge_foursales.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Transactional
    public Page<OrderResponse> findByUserId(String userId, Pageable pagination) {
        UUID uuid = UUID.fromString(userId);

        Page<Order> ordersPages = orderRepository.findByUserId(uuid, pagination);

        return ordersPages.map(OrderResponse::new);
    }

    public Order findById(BigInteger id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        return optionalOrder.orElse(null);
    }

    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = new Order();
        order.setStatus(Status.PENDING);

        User user = userService.findById(orderRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);

        float totalOrder = 0.0f;

        for (OrderItemRequest itemRequest : orderRequest.orderItems()) {
            Product product = productService.findById(itemRequest.productId());

            Boolean productInStock = validateStockProduct(product, itemRequest.quantity());

            if (!productInStock) {
                throw new InsufficientStockException("Pedido cancelado estoque insuficiente para o produto: " + product.getName());
            }

            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal itemTotal = productPrice.multiply(BigDecimal.valueOf(itemRequest.quantity()));

            totalOrder += productPrice.floatValue();

            OrderItem orderItem = new OrderItem(product, itemRequest.quantity());
            order.addOrderItem(orderItem);
        }

        order.setTotal(totalOrder);

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(savedOrder);
    }

    private boolean validateStockProduct(Product product, int quantity) {
        return product.getStock() >= quantity;
    }

    @Transactional
    public Order update(BigInteger id, Order order) {
        Order orderFound = findById(id);

        if (orderFound != null) {
            return orderRepository.save(order);
        } else {
            return order;
        }
    }

    public Page<UserCountResponse> getTop5UsersWithMostOrders(Pageable pagination) {
        Page<Object[]> resultPage = orderRepository.findTopUsersByOrderCount(pagination);

        return resultPage.map(row -> new UserCountResponse(
                (UUID) row[0],
                (String) row[1],
                (Long) row[2]
        ));
    }

    public Float getTotalBilledInMonth() {
        Float valorTotal = orderRepository.findTotalBilledInMonth();
        return valorTotal != null ? valorTotal : 0.0f;
    }
}
