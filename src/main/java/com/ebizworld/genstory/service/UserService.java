package com.ebizworld.genstory.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebizworld.genstory.constant.PredefinedRole;
import com.ebizworld.genstory.dto.request.UserCreationRequest;
import com.ebizworld.genstory.dto.request.UserUpdateRequest;
import com.ebizworld.genstory.dto.response.UserResponse;
import com.ebizworld.genstory.entity.Role;
import com.ebizworld.genstory.entity.User;
import com.ebizworld.genstory.exception.AppException;
import com.ebizworld.genstory.exception.ErrorCode;
import com.ebizworld.genstory.mapper.UserMapper;
import com.ebizworld.genstory.repository.RoleRepository;
import com.ebizworld.genstory.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor // Tạo constructor tự động với tất cả các trường thay cho @Autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(role -> roles.add(role));
        user.setRoles(roles);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('APPROVE_DATA')") //Có quyền APPROVE_POST
    // getUsers
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name") // người dùng hiện tại mới có quyền truy cập
    public UserResponse getUser(String userId) {
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext(); // lấy thông tin người dùng hiện tại từ SecurityContext
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user); // sử dụng mapper để chuyển đổi sang UserResponse
    }
}
