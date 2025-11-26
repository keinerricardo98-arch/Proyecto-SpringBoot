package com.autenticacion.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String authMethod;

    @Column(nullable = false)
    private LocalDateTime attemptTime;

    @Column(nullable = false)
    private Boolean success;

    private String ipAddress;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    @PrePersist
    protected void onCreate() {
        if (attemptTime == null) {
            attemptTime = LocalDateTime.now();
        }
    }
}