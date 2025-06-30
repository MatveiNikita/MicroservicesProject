package com.example.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String username;

    private String email;

    private String password;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
