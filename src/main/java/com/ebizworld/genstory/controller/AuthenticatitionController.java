package com.ebizworld.genstory.controller;

import java.text.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebizworld.genstory.dto.request.AuthenticationRequest;
import com.ebizworld.genstory.dto.request.IntrospectRequest;
import com.ebizworld.genstory.dto.request.LogoutRequest;
import com.ebizworld.genstory.dto.request.RefreshRequest;
import com.ebizworld.genstory.dto.response.ApiResponse;
import com.ebizworld.genstory.dto.response.AuthenticationResponse;
import com.ebizworld.genstory.dto.response.IntrospectResponse;
import com.ebizworld.genstory.service.AuthencationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // gán mặc định cho các trường là final và private
public class AuthenticatitionController {
    AuthencationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        var isAuthenticated = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(isAuthenticated)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws JOSEException, ParseException {
        var isAuthenticated = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(isAuthenticated)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        var isAuthenticated = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(isAuthenticated).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }
}
