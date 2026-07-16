package com.bhojanalay.auth.mapper;

import com.bhojanalay.auth.dto.request.RegisterRequest;
import com.bhojanalay.auth.dto.response.RegisterResponse;
import com.bhojanalay.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    User toEntity(RegisterRequest request);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "message", constant = "User registered successfully")
    RegisterResponse toResponse(User user);
}