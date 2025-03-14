package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.dto.OrderRequest;
import com.davijose.challenge_foursales.dto.OrderResponse;
import com.davijose.challenge_foursales.dto.UserCountResponse;
import com.davijose.challenge_foursales.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity create(@RequestBody @Valid OrderRequest datas, UriComponentsBuilder uriBuilder) {
        OrderResponse orderResponse = orderService.save(datas);

        URI uri = uriBuilder.path("/orders/{id}")
                .buildAndExpand(orderResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(orderResponse);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<Page<OrderResponse>> getOrdersByIdUser(@PathVariable String id, @PageableDefault(size = 10) Pageable pagination) {

        Page<OrderResponse> responsePage = orderService.findByUserId(id, pagination);

        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/top")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Page<UserCountResponse>> getTop5WithMostOrders(@PageableDefault(size = 5) Pageable pagination) {
        Page<UserCountResponse> usersCount = orderService.getTop5UsersWithMostOrders(pagination);

        return ResponseEntity.ok(usersCount);
    }
    @GetMapping("/total-invoiced-month")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Float getTotalBilledInMonth() {
        return orderService.getTotalBilledInMonth();
    }
}
