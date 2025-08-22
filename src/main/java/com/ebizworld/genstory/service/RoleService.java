package com.ebizworld.genstory.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebizworld.genstory.dto.request.RoleRequest;
import com.ebizworld.genstory.dto.response.RoleResponse;
import com.ebizworld.genstory.mapper.RoleMapper;
import com.ebizworld.genstory.repository.PermissionRepository;
import com.ebizworld.genstory.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permission = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getRoleAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
