package com.ebizworld.genstory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ebizworld.genstory.dto.request.UserCreationRequest;
import com.ebizworld.genstory.dto.request.UserUpdateRequest;
import com.ebizworld.genstory.dto.response.UserResponse;
import com.ebizworld.genstory.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
