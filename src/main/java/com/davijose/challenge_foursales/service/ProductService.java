package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.dto.ProductRequestUpdate;
import com.davijose.challenge_foursales.dto.ProductResponse;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.error.InsufficientStockException;
import com.davijose.challenge_foursales.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Page<ProductResponse> findProducts(Pageable pagination) {
        Page<Product> productsPage = productRepository.findAll(pagination);

        return productsPage.map(ProductResponse::new);
    }

    @Transactional
    public Product findById(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(UUID id, ProductRequestUpdate productRequest) {
        Product productFound = findById(id);

        if (productFound == null) {
            throw new EntityNotFoundException("Produto não encontrado com o id: " + id);
        }

        if (productRequest.name() != null) {
            productFound.setName(productRequest.name());
        }
        if (productRequest.description() != null) {
            productFound.setDescription(productRequest.description());
        }
        if (productRequest.price() != null) {
            productFound.setPrice(productRequest.price());
        }
        if (productRequest.stock() != null) {
            productFound.setStock(productRequest.stock());
        }
        if (productRequest.category() != null) {
            productFound.setCategory(productRequest.category());
        }

        return productRepository.save(productFound);

    }

    @Transactional
    public void delete(UUID id) {
        Product productFound = findById(id);
        if (productFound == null) {
            throw new EntityNotFoundException("Produto não encontrado com o id: " + id);
        }
        productRepository.delete(productFound);
    }

    @Transactional
    public void validateStock(UUID productId) {
        Product product = findById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        if (product.getStock() <= 0) {
            throw new RuntimeException("Insufficient stock for product with id: " + productId);
        }
    }

    @Transactional
    public void decrementStock(UUID productId, int quantity) {
        Product product = findById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        if (product.getStock() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product with id: " + productId);
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }
}
