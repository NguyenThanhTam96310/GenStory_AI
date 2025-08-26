package com.ebizworld.genstory.config;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ebizworld.genstory.constant.AppConstants;
import com.ebizworld.genstory.constant.PredefinedRole;
import com.ebizworld.genstory.entity.Role;
import com.ebizworld.genstory.entity.User;
import com.ebizworld.genstory.repository.RoleRepository;
import com.ebizworld.genstory.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(AppConstants.ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username(AppConstants.ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(AppConstants.ADMIN_PASSWORD))
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
