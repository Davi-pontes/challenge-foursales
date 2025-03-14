package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.controller.dto.ProductResponse;
import com.davijose.challenge_foursales.controller.dto.UserAverageTicketResponse;
import com.davijose.challenge_foursales.domain.user.User;
import com.davijose.challenge_foursales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/average-ticket")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserAverageTicketResponse>> getAverageTicketByUser() {
        List<UserAverageTicketResponse> result = userService.getAverageTicketByUser();
        return ResponseEntity.ok(result);
    }
}
