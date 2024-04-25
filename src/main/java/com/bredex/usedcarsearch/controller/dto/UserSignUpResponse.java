package com.bredex.usedcarsearch.controller.dto;

import lombok.Builder;

@Builder
public record UserSignUpResponse(String username, String email, String messageResponse) {
}
