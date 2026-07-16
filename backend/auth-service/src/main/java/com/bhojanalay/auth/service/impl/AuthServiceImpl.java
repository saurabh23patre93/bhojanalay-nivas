package com.bhojanalay.auth.service.impl;

import com.bhojanalay.auth.dto.request.RegisterRequest;
import com.bhojanalay.auth.dto.response.RegisterResponse;
import com.bhojanalay.auth.entity.Role;
import com.bhojanalay.auth.entity.User;
import com.bhojanalay.auth.mapper.UserMapper;
import com.bhojanalay.auth.repository.RoleRepository;
import com.bhojanalay.auth.repository.UserRepository;
import com.bhojanalay.auth.service.AuthService;
import com.bhojanalay.common.bootstrep.SecurityConstants;
import com.bhojanalay.common.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

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
}