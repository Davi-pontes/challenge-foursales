package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
    Page<Order> findByUserId(UUID userId, Pageable pagination);
}
