package com.ebizworld.genstory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ebizworld.genstory.dto.request.RoleRequest;
import com.ebizworld.genstory.dto.response.RoleResponse;
import com.ebizworld.genstory.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true) // Mình không ánh xạ permissions từ RoleRequest sang Role vì nó là
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
