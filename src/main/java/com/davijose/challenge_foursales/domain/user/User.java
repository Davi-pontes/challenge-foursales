package com.davijose.challenge_foursales.domain.user;

import com.davijose.challenge_foursales.dto.ProductRequest;
import com.davijose.challenge_foursales.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    public User(UserRequest datas) {
        this.name = datas.name();
        this.email = datas.email();
        this.password = datas.password();
        this.roleUser = datas.roleUser();
    }
}
