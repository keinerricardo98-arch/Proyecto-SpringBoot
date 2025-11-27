package com.autenticacion.service;

import com.autenticacion.dto.AuthResponse;
import com.autenticacion.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class TwoFactorService {

    private final Map<String, User> twoFASessions = new HashMap<>();
    private final Map<String, User> authTokens = new HashMap<>();

    public String generate2FASession(User user) {
        String sessionToken = UUID.randomUUID().toString();
        twoFASessions.put(sessionToken, user);
        log.info("Sesión 2FA generada para usuario: {}", user.getUsername());

        // En un sistema real, aquí enviarías el código por email/SMS
        log.info("🔐 Código 2FA para {}: 123456", user.getUsername());

        return sessionToken;
    }

    public AuthResponse verify2FACode(String sessionToken, String code) {
        User user = twoFASessions.get(sessionToken);

        if (user == null) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Sesión 2FA inválida o expirada")
                    .build();
        }

        // Validar código (en demo, solo acepta 123456)
        if ("123456".equals(code)) {
            twoFASessions.remove(sessionToken);
            String authToken = generateAuthToken(user);

            return AuthResponse.builder()
                    .success(true)
                    .message("Autenticación 2FA exitosa")
                    .token(authToken)
                    .user(AuthResponse.UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build())
                    .twoFactorRequired(false)
                    .build();
        } else {
            return AuthResponse.builder()
                    .success(false)
                    .message("Código 2FA incorrecto")
                    .build();
        }
    }

    public String generateAuthToken(User user) {
        String token = UUID.randomUUID().toString();
        authTokens.put(token, user);
        return token;
    }

    public User validateToken(String token) {
        return authTokens.get(token);
    }

    public void logout(String token) {
        authTokens.remove(token);
    }
}