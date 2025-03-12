package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
