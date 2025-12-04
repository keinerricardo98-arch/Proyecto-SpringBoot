package com.autenticacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "El identificador es obligatorio")
    private String identifier;

    @NotBlank(message = "La credencial es obligatoria")
    private String credential;

    private String authMethod;
    private Boolean twoFactorEnabled;
}