package com.autenticacion.service;

import com.autenticacion.dto.AuthResponse;
import com.autenticacion.dto.LoginRequest;
import com.autenticacion.entity.AuthLog;
import com.autenticacion.entity.User;
import com.autenticacion.repository.AuthLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final AuthLogRepository authLogRepository;
    private final TwoFactorService twoFactorService;

    public AuthResponse authenticate(LoginRequest request, HttpServletRequest httpRequest) {
        try {
            // Buscar usuario por username o email
            var userOpt = userService.findByUsernameOrEmail(request.getIdentifier());

            if (userOpt.isEmpty()) {
                logAuthAttempt(null, request.getAuthMethod(), false, httpRequest);
                return AuthResponse.builder()
                        .success(false)
                        .message("Usuario no encontrado")
                        .build();
            }

            User user = userOpt.get();

            // Validar contraseña
            if (!userService.validatePassword(request.getCredential(), user.getPassword())) {
                logAuthAttempt(user, request.getAuthMethod(), false, httpRequest);
                return AuthResponse.builder()
                        .success(false)
                        .message("Credenciales incorrectas")
                        .build();
            }

            // Verificar si requiere 2FA
            if (Boolean.TRUE.equals(request.getTwoFactorEnabled())) {
                String sessionToken = twoFactorService.generate2FASession(user);
                logAuthAttempt(user, request.getAuthMethod(), true, httpRequest);

                return AuthResponse.builder()
                        .success(true)
                        .message("Verificación 2FA requerida")
                        .twoFactorRequired(true)
                        .authMethod(request.getAuthMethod())
                        .build();
            }

            // Autenticación exitosa
            String token = twoFactorService.generateAuthToken(user);
            logAuthAttempt(user, request.getAuthMethod(), true, httpRequest);

            return AuthResponse.builder()
                    .success(true)
                    .message("Autenticación exitosa")
                    .token(token)
                    .user(AuthResponse.UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build())
                    .authMethod(request.getAuthMethod())
                    .twoFactorRequired(false)
                    .build();

        } catch (Exception e) {
            log.error("Error en autenticación: {}", e.getMessage());
            return AuthResponse.builder()
                    .success(false)
                    .message("Error interno del servidor")
                    .build();
        }
    }

    private void logAuthAttempt(User user, String authMethod, boolean success, HttpServletRequest request) {
        AuthLog authLog = AuthLog.builder()
                .user(user)
                .authMethod(authMethod != null ? authMethod : "password")
                .success(success)
                .ipAddress(getClientIpAddress(request))
                .userAgent(request.getHeader("User-Agent"))
                .build();

        authLogRepository.save(authLog);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}