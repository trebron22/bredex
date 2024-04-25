package com.bredex.usedcarsearch.controller.dto;

import lombok.Builder;

@Builder
public record UserLoginResponse(String accessToken, String refreshToken, String message) {
}
