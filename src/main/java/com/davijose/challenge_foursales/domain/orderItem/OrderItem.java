package com.davijose.challenge_foursales.domain.orderItem;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.domain.product.Product;
import com.davijose.challenge_foursales.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "order_items")
@Entity(name = "OrderItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updateAt;
}
