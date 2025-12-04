package com.autenticacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TwoFactorRequest {

    @NotBlank(message = "El código 2FA es obligatorio")
    private String code;

    private String sessionToken;
}