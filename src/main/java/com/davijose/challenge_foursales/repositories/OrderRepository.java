package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
    @Query("SELECT u.id, u.name, COUNT(o) as totalOrders " +
            "FROM Order o " +
            "LEFT JOIN user u " +
            "GROUP BY u.id, u.name " +
            "ORDER BY totalOrders DESC")
    Page<Object[]> findTopUsersByOrderCount(Pageable pagination);

    @Query("SELECT SUM(o.total) FROM Order o " +
            "WHERE YEAR(o.createdAt) = YEAR(CURRENT_DATE) " +
            "AND MONTH(o.createdAt) = MONTH(CURRENT_DATE)")
    Float findTotalBilledInMonth();

    Page<Order> findByUserId(UUID userId, Pageable pagination);
}
