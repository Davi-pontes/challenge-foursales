package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.dto.UserAverageTicketResponse;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.dto.UserRequest;
import com.davijose.challenge_foursales.dto.UserResponse;
import com.davijose.challenge_foursales.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest datas, UriComponentsBuilder uriBuilder){
        User user = new User(datas);

        UserResponse userResponse = userService.save(user);

        URI uri = uriBuilder.path("/products/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userResponse);
    }
    @GetMapping("/average-ticket")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserAverageTicketResponse>> getAverageTicketByUser() {
        List<UserAverageTicketResponse> result = userService.getAverageTicketByUser();
        return ResponseEntity.ok(result);
    }
}
