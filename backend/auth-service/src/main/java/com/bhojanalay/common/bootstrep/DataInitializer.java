package com.bhojanalay.common.bootstrep;

import com.bhojanalay.auth.entity.Role;
import com.bhojanalay.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRole("ROLE_ADMIN");

        createRole("ROLE_OWNER");

        createRole("ROLE_CUSTOMER");

    }

    private void createRole(String role) {

        if (!roleRepository.existsByName(role)) {

            roleRepository.
                    save(Role.builder().
                            name(role).
                            description(role).
                            build());
        }
    }
}
