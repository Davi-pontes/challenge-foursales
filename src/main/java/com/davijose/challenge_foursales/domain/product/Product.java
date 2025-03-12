package com.davijose.challenge_foursales.domain.product;

import com.davijose.challenge_foursales.domain.category.Category;
import com.davijose.challenge_foursales.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private Float price;
    private int stock;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updateAt;
}