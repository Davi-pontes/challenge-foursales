package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.dto.UserAverageTicketResponse;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.dto.UserRequest;
import com.davijose.challenge_foursales.dto.UserResponse;
import com.davijose.challenge_foursales.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new UserResponse(user.getId(),user.getName(), user.getEmail(), user.getRoleUser());
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("Usuários não encontrados.");
        }
        return users;
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        return user.orElseThrow(() -> new BadCredentialsException("Usuário ou senha incorreto."));
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);

    }
    public List<UserAverageTicketResponse> getAverageTicketByUser() {
        List<Object[]> result = userRepository.findAverageTicketByUser();

        return result.stream()
                .map(row -> new UserAverageTicketResponse(
                        (UUID) row[0],
                        (String) row[1],
                        (Double) row[2]
                ))
                .collect(Collectors.toList());
    }

}
