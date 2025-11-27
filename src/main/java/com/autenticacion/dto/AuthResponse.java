package com.autenticacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private boolean success;
    private String message;
    private String token;
    private UserDTO user;
    private String authMethod;
    private Boolean twoFactorRequired;

    @Data
    @Builder
    public static class UserDTO {
        private Long id;
        private String username;
        private String email;
    }
}