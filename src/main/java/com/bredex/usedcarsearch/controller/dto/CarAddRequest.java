package com.bredex.usedcarsearch.controller.dto;

import com.bredex.usedcarsearch.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarAddRequest {

    @Column(length = 20)
    @Size(max = 20, message = "Type must be less than 20 character")
    private String brand;

    @Column(length = 20)
    @Size(max = 20, message = "Type must be less than 20 character")
    private String type;

    @Column(length = 200)
    @Size(max = 200, message = "Description must be less than 200 character")
    private String description;

    @Column(length = 10)
    private Long price;

    @NotNull
    private Long userId;

}
