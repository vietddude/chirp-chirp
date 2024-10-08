# Chirp Chirp

## Overview
This project is a Spring Boot application that uses Gradle for build automation. It includes configurations for database migrations using Flyway, Redis connection using Jedis, and password encoding using BCrypt.

## Prerequisites
- Java 11 or higher
- Gradle
- PostgreSQL
- Redis

## Setup

### Clone the Repository
```sh
git clone https://github.com/vietddude/chirp-chirp.git
cd chirp-chirp
```

### Configuration
Update the `application.properties` or `application.yml` file with your database and Redis configurations.

## Running the Application
```sh
./gradlew bootRun
```

## Database Migration
Flyway is used for database migrations. It automatically runs migrations on application startup.

## Functions

### Flyway Configuration
- **FlywayConfig**: Configures Flyway for database migrations.

### Redis Configuration
- **RedisConfig**: Configures Redis connection using Jedis.

### Password Encoding
- **PasswordConfig**: Configures BCrypt for password encoding.

### JWT Token Management
- **JwtTokenManager**: Manages JWT tokens, including generation and validation.

## Logging
Ensure logging is properly configured in `application.properties` or `application.yml` to capture application logs.

## Dependencies
- Spring Boot
- Flyway
- Jedis
- BCrypt
- SLF4J
- Logback

## License
This project is licensed under the MIT License. See the LICENSE file for details.