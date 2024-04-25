package com.bredex.usedcarsearch.controller.dto;

import lombok.Builder;

@Builder
public record UserLoginRequest(String email, String password) {
}
