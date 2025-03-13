package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.controller.dto.OrderRequest;
import com.davijose.challenge_foursales.controller.dto.OrderResponse;
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
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity create(@RequestBody @Valid OrderRequest datas, UriComponentsBuilder uriBuilder){
        OrderResponse orderResponse = orderService.save(datas);

        URI uri = uriBuilder.path("/orders/{id}")
                .buildAndExpand(orderResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(orderResponse);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Page<OrderResponse>> getOrdersByIdUser(@PathVariable UUID id, @PageableDefault(size = 10) Pageable pagination){
        Page<OrderResponse> responsePage = orderService.findByUserId(id, pagination);

        return ResponseEntity.ok(responsePage);
    }
}
