package com.davijose.challenge_foursales.domain.product;

import com.davijose.challenge_foursales.controller.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Float price;
    private int stock;
    private String category;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public Product(ProductRequest datas) {
        this.name = datas.name();
        this.description = datas.description();
        this.price = datas.price();
        this.stock = datas.stock();
        this.category = datas.category();
    }
}