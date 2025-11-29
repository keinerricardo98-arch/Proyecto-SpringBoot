package com.autenticacion.controller;

import com.autenticacion.dto.AuthResponse;
import com.autenticacion.dto.LoginRequest;
import com.autenticacion.dto.TwoFactorRequest;
import com.autenticacion.service.AuthService;
import com.autenticacion.service.TwoFactorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    private final AuthService authService;
    private final TwoFactorService twoFactorService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest) {
        
        log.info("Intento de login: {} - Método: {}", request.getIdentifier(), request.getAuthMethod());
        
        AuthResponse response = authService.authenticate(request, httpRequest);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/verify-2fa")
    public ResponseEntity<AuthResponse> verifyTwoFactor(
            @Valid @RequestBody TwoFactorRequest request) {
        
        log.info("Verificación 2FA para sesión: {}", request.getSessionToken());
        
        AuthResponse response = twoFactorService.verify2FACode(
                request.getSessionToken(), 
                request.getCode()
        );
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(@RequestHeader("Authorization") String token) {
        String authToken = token.replace("Bearer ", "");
        twoFactorService.logout(authToken);
        
        log.info("Usuario cerró sesión");
        
        return ResponseEntity.ok(AuthResponse.builder()
                .success(true)
                .message("Sesión cerrada exitosamente")
                .build());
    }
    
    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validateToken(@RequestHeader("Authorization") String token) {
        String authToken = token.replace("Bearer ", "");
        var user = twoFactorService.validateToken(authToken);
        
        if (user != null) {
            return ResponseEntity.ok(AuthResponse.builder()
                    .success(true)
                    .message("Token válido")
                    .user(AuthResponse.UserDTO.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .email(user.getEmail())
                            .build())
                    .build());
        } else {
            return ResponseEntity.ok(AuthResponse.builder()
                    .success(false)
                    .message("Token inválido o expirado")
                    .build());
        }
    }
}