package com.autenticacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaAutenticacionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaAutenticacionApplication.class, args);
        System.out.println("Sistema de Autenticación iniciado correctamente!");
        System.out.println("Backend running on: http://localhost:8080");
        System.out.println("Frontend should run on: http://localhost:8080");
        System.out.println("[Listo para autenticar usuarios]");
    }
}