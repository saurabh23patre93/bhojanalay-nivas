package com.bhojanalay.auth.entity;

import com.bhojanalay.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@SuperBuilder
public class RefreshToken extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private boolean revoked;
}