package com.bhojanalay.auth.service.impl;

import com.bhojanalay.auth.entity.RefreshToken;
import com.bhojanalay.auth.entity.User;
import com.bhojanalay.auth.repository.RefreshTokenRepository;
import com.bhojanalay.auth.service.RefreshTokenService;
import com.bhojanalay.common.exception.RefreshTokenExpiredException;
import com.bhojanalay.common.exception.RefreshTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${security.refresh-token.expiration-seconds:1209600}") // 14 days default
    private Long refreshTokenExpirationSeconds;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public RefreshToken createRefreshToken(User user) {
        String token = generateToken();
        Instant expiresAt = Instant.now().plusSeconds(refreshTokenExpirationSeconds);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiresAt(expiresAt)
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken rt = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RefreshTokenNotFoundException(token));

        if (rt.isRevoked()) {
            throw new RefreshTokenExpiredException("Refresh token revoked");
        }

        if (rt.getExpiresAt().isBefore(Instant.now())) {
            throw new RefreshTokenExpiredException("Refresh token expired");
        }

        return rt;
    }

    @Override
    public void revokeToken(String token) {
        Optional<RefreshToken> maybe = refreshTokenRepository.findByToken(token);
        maybe.ifPresent(rt -> {
            rt.setRevoked(true);
            refreshTokenRepository.save(rt);
        });
    }

    @Override
    public void revokeAllForUser(UUID userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }

    private String generateToken() {
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}

