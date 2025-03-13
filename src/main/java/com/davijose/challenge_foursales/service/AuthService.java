package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.controller.dto.LoginRequest;
import com.davijose.challenge_foursales.domain.user.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public User validate(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.email());

        if (!passwordEncoder.matches(loginRequest.password(),user.getPassword())) {
            throw new BadCredentialsException("Senha incorreta.");
        }

        return user;
    }
}
