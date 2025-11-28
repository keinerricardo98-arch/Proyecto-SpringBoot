package com.autenticacion.controller;

import com.autenticacion.entity.AuthLog;
import com.autenticacion.repository.AuthLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    private final AuthLogRepository authLogRepository;
    
    @GetMapping("/logs")
    public ResponseEntity<List<AuthLog>> getAuthLogs() {
        List<AuthLog> logs = authLogRepository.findTop10ByOrderByAttemptTimeDesc();
        return ResponseEntity.ok(logs);
    }
    
    @GetMapping("/logs/{userId}")
    public ResponseEntity<List<AuthLog>> getUserAuthLogs(@PathVariable Long userId) {
        List<AuthLog> logs = authLogRepository.findByUserIdOrderByAttemptTimeDesc(userId);
        return ResponseEntity.ok(logs);
    }
}