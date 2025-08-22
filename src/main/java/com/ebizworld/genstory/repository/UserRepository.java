package com.ebizworld.genstory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebizworld.genstory.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String userName);

    Optional<User> findByUsername(String userName);
}
