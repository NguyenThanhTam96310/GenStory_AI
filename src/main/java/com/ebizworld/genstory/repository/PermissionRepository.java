package com.ebizworld.genstory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebizworld.genstory.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {}
