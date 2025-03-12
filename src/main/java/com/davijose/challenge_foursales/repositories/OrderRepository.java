package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
