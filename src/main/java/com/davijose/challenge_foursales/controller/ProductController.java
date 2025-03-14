package com.davijose.challenge_foursales.controller;

import com.davijose.challenge_foursales.dto.ProductRequestUpdate;
import com.davijose.challenge_foursales.dto.ProductResponse;
import com.davijose.challenge_foursales.dto.ProductRequest;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') || hasAuthority('SCOPE_USER')")
    public ResponseEntity<Page<ProductResponse>> findAll(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {

        Page<ProductResponse> responsePage = productService.findProducts(pagination);

        return ResponseEntity.ok(responsePage);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest datas, UriComponentsBuilder uriBuilder) {
        Product product = new Product(datas);

        product = productService.save(product);

        ProductResponse response = new ProductResponse(product);

        URI uri = uriBuilder.path("/products/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
    @PutMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequestUpdate datas){
        Product product = productService.update(datas.id(),datas);

        return ResponseEntity.ok(new ProductResponse(product));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
