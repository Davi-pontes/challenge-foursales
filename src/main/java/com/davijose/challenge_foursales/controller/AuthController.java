package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.controller.dto.LoginRequest;
import com.davijose.challenge_foursales.controller.dto.LoginResponse;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class AuthController {

    private final JwtEncoder jwtEncoder;
    private final AuthService authService;

    public AuthController(JwtEncoder jwtEncoder, AuthService authService) {
        this.jwtEncoder = jwtEncoder;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.validate(loginRequest);
        var now = Instant.now();
        var expiresIn = 300L;
        var scopes = user.getRoleUser().name();

        JwtClaimsSet  claims = JwtClaimsSet.builder()
                .issuer("myService")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();
        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue,1800L));
    }
}
