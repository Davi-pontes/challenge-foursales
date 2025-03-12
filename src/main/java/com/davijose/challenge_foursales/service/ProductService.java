package com.davijose.challenge_foursales.service;

import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product findById(UUID id){
        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public Product update(UUID id, Product product){
        Product productFound = findById(id);

        if(productFound != null){
            return productRepository.save(product);
        }else{
            return product;
        }
    }
}
