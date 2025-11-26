package com.autenticacion.repository;

import com.autenticacion.entity.AuthLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {

    List<AuthLog> findByUserIdOrderByAttemptTimeDesc(Long userId);
    List<AuthLog> findTop10ByOrderByAttemptTimeDesc();
}