package com.incubyte.sweetshop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Payload for login request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
