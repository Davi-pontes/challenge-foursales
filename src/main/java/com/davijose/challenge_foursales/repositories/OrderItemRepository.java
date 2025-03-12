package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
