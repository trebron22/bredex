package com.bredex.usedcarsearch.controller.dto;

import com.bredex.usedcarsearch.model.Role;
import com.bredex.usedcarsearch.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSignUpRequest {

    @Column(length = 50)
    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be less than 50 character")
    @NotNull
    private String name;

    @Column(unique = true)
    @NotNull
    @Email(message = "Invalid email address format")
    private String email;

    @Column(length = 100)
    @NotBlank
    @NotNull
    @Size(min = 8, max = 100)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    private String password;

    @NotNull(message = "Role Must not be null")
    private Role role;
}
