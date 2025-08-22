package com.ebizworld.genstory.mapper;

import org.mapstruct.Mapper;

import com.ebizworld.genstory.dto.request.PermissionRequest;
import com.ebizworld.genstory.dto.response.PermissionResponse;
import com.ebizworld.genstory.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
