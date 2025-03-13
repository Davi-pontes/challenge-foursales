package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.controller.dto.ProductRequestUpdate;
import com.davijose.challenge_foursales.controller.dto.ProductResponse;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public  Page<ProductResponse> findProducts(Pageable pagination){
        Page<Product> productsPage = productRepository.findAll(pagination);

        return productsPage.map(ProductResponse::new);
    }
    public Product findById(UUID id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public Product update(UUID id, ProductRequestUpdate productRequest) {
        Product productFound = findById(id);

        if (productFound != null) {
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
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
    @Transactional
    public void delete(UUID id) {
        Product productFound = findById(id);
        productRepository.delete(productFound);
    }
}
