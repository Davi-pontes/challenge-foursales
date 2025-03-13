package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.controller.dto.OrderRequest;
import com.davijose.challenge_foursales.controller.dto.OrderResponse;
import com.davijose.challenge_foursales.controller.dto.ProductRequest;
import com.davijose.challenge_foursales.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderIController {
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
}
