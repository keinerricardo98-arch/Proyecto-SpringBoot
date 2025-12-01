# 🔐 Sistema de Autenticación - Spring Boot & React

Sistema completo de autenticación implementando patrones de diseño con Spring Boot, JPA, MySQL y React.

## 🎯 Características

- ✅ Múltiples métodos de autenticación (Strategy Pattern)
- ✅ Base de datos MySQL con JPA/Hibernate
- ✅ Autenticación en Dos Pasos (2FA)
- ✅ Registro de logs de seguridad
- ✅ API REST con Spring Boot
- ✅ Frontend moderno con React
- ✅ Seguridad con Spring Security

## 🛠️ Stack Tecnológico

### Backend
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- Maven

### Frontend
- React 18
- Axios
- CSS3

## 🚀 Instalación y Ejecución

### Prerrequisitos
- Java 17
- Node.js 16+
- MySQL 8.0+

### 1. Base de Datos
```sql
CREATE DATABASE auth_system;
2. Backend
bash
cd backend
./mvnw spring-boot:run
3. Frontend
bash
cd frontend
npm install
npm start
🧪 Credenciales de Prueba
Método	Usuario/Email	Credencial
Password	usuario1	password123
Google	cualquier@email.com	google_token
Facebook	cualquier@email.com	facebook_token
2FA	-	123456
📊 Patrones de Diseño Implementados
Strategy: Diferentes métodos de autenticación

Factory: Creación de servicios

Repository: Acceso a datos con JPA

DTO: Transferencia de datos

MVC: Arquitectura de la aplicación

🔐 Endpoints API
POST /api/auth/login - Autenticación

POST /api/auth/verify-2fa - Verificación 2FA

GET /api/auth/validate - Validación de token

GET /api/users/logs - Logs de autenticación

📋 Información Académica
Universidad: [Universidad de cordoba]
Facultad: [Ingenierias]
Carrera: [Ingenieria de sistemas]
Asignatura: [Programacion III]
Docente: [Alberto patermina]
Email del Docente: [pater8715@gmail.com]
Semestre: [IV]
Estudiante: [Keiner ricardo]
Estudiante: [Pedro gimenez]

👨‍💻 Desarrollado por
[Keiner ricardo]
[Pedro gimenez]


📄 Licencia
Este proyecto es con fines educativos.

text

### **📄 SETUP.md (instrucciones detalladas)**
```markdown
# 🛠️ Guía de Configuración Completa

## Requisitos Previos
- Java JDK 17
- Node.js 16+
- MySQL 8.0+
- IntelliJ IDEA
- VS Code

## Configuración Paso a Paso

### 1. Configurar MySQL
1. Descargar MySQL Installer
2. Instalar MySQL Server
3. Configurar contraseña: 123456
4. Crear base de datos: `auth_system`

### 2. Configurar Backend
1. Abrir proyecto en IntelliJ
2. Ejecutar `SistemaAutenticacionApplication`
3. Verificar en: http://localhost:8080

### 3. Configurar Frontend
1. Abrir carpeta frontend en VS Code
2. Ejecutar `npm install`
3. Ejecutar `npm start`
4. Verificar en: http://localhost:3000

## Estructura del Proyecto
sistema-autenticacion/
├── backend/ # Spring Boot
├── frontend/ # React
└── README.md


         

     
