package com.bredex.usedcarsearch.controller.dto;

import lombok.Builder;

@Builder
public record DeleteRequest(Long carId, Long userId) {
}
