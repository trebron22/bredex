package com.bredex.usedcarsearch.controller;

import com.bredex.usedcarsearch.controller.dto.*;
import com.bredex.usedcarsearch.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Sign up", description = "Register a new user")
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponse> signUp(
            @Valid @RequestBody UserSignUpRequest userSignUpRequest) {
        return authService.signUp(userSignUpRequest);
    }

    @Operation(summary = "Login", description = "User login")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(
            @RequestBody UserLoginRequest userLoginRequest) {
        return authService.login(userLoginRequest);
    }

    @Operation(summary = "Refresh token", description = "Refresh authentication token")
    @PostMapping("/refresh-token")
    public void refreshToken(
            @Parameter(description = "HTTP request") HttpServletRequest request,
            @Parameter(description = "HTTP response") HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

}
