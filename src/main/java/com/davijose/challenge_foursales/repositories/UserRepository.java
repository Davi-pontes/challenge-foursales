package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u.id, u.name, AVG(o.total) " +
            "FROM Order o " +
            "JOIN o.user u " +
            "GROUP BY u.id, u.name")
    List<Object[]> findAverageTicketByUser();

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
