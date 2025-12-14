package com.incubyte.sweetshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Payload for user registration.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
