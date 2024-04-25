package com.bredex.usedcarsearch.service;

import com.bredex.usedcarsearch.controller.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

    public ResponseEntity<UserSignUpResponse> signUp(UserSignUpRequest userSignUpRequest);

    ResponseEntity<UserLoginResponse> login(UserLoginRequest userLoginRequest);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
