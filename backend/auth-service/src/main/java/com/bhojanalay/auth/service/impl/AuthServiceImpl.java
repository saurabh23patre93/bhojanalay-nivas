package com.bhojanalay.auth.service.impl;

import com.bhojanalay.auth.dto.request.LogoutRequest;
import com.bhojanalay.auth.dto.request.RefreshTokenRequest;
import com.bhojanalay.auth.dto.response.RefreshTokenResponse;
import com.bhojanalay.auth.entity.RefreshToken;
import com.bhojanalay.auth.jwt.JwtService;
import com.bhojanalay.auth.dto.request.LoginRequest;
import com.bhojanalay.auth.dto.request.RegisterRequest;
import com.bhojanalay.auth.dto.response.LoginResponse;
import com.bhojanalay.auth.dto.response.RegisterResponse;
import com.bhojanalay.auth.entity.Role;
import com.bhojanalay.auth.entity.User;
import com.bhojanalay.auth.mapper.UserMapper;
import com.bhojanalay.auth.repository.RoleRepository;
import com.bhojanalay.auth.repository.UserRepository;
import com.bhojanalay.auth.service.AuthService;
import com.bhojanalay.auth.service.RefreshTokenService;
import com.bhojanalay.common.bootstrep.SecurityConstants;
import com.bhojanalay.common.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new MobileAlreadyExistsException(request.getMobile());
        }

        Role customerRole = roleRepository.findByName(SecurityConstants.ROLE_CUSTOMER).orElseThrow(() -> new RoleNotFoundException(SecurityConstants.ROLE_CUSTOMER));

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.getRoles().add(customerRole);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findWithRolesByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String accessToken =
                jwtService.generateAccessToken(user);

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiry())
                .build();
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());

        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user.getEmail());

        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .tokenType("Bearer")
                .expiresIn(jwtService.getAccessTokenExpiry())
                .build();
    }

    @Override
    public void logout(LogoutRequest request) {
        refreshTokenService.revokeToken(request.getRefreshToken());
    }
}