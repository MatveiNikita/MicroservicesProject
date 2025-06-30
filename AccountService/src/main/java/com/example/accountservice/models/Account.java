package com.example.accountservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "user_id", nullable = false, updatable = false)
    private String handlerId;

    private BigDecimal balance;

    @CreationTimestamp
    @Column(name = "created_time")
    private LocalDateTime createdTime;
}
