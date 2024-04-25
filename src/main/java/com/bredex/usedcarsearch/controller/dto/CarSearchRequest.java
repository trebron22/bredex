package com.bredex.usedcarsearch.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarSearchRequest {
    @Size(max = 50)
    private String brand;

    @Size(max = 20)
    private String type;

    private Long price;

}
