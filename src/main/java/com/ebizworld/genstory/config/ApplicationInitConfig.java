package com.ebizworld.genstory.config;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ebizworld.genstory.enums.Role;
import com.ebizworld.genstory.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import lombok.AccessLevel;

@Configuration
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                var user = userRepository.save(
                        com.ebizworld.genstory.entity.User.builder()
                                .username(ADMIN_USER_NAME)
                                .password(passwordEncoder.encode(ADMIN_PASSWORD))
                                .firstName("Admin")
                                .lastName("User")
                                // .roles(roles)
                                .build());

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
