package com.bhojanalay.auth.common.bootstrap;

import com.bhojanalay.auth.common.constants.SecurityConstants;
import com.bhojanalay.auth.entity.Role;
import com.bhojanalay.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole(SecurityConstants.ROLE_ADMIN, "System Administrator");

        createRole(SecurityConstants.ROLE_OWNER, "Restaurant Owner");

        createRole(SecurityConstants.ROLE_CUSTOMER, "Application Customer");

        log.info("Default roles initialized.");
    }

    private void createRole(String roleName, String description) {

        if (!roleRepository.existsByName(roleName)) {

            Role role = Role.builder().name(roleName).description(description).build();

            roleRepository.save(role);

            log.info("Created role: {}", roleName);
        }
    }
}
