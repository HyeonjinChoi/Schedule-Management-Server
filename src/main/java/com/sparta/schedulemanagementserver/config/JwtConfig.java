package com.sparta.schedulemanagementserver.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;

    @Value("3600000")
    private Long EXPIRATION;

    public static String staticSecretKey;
    public static Long staticExpiration;

    @PostConstruct
    public void init() {
        staticSecretKey = SECRET_KEY;
        staticExpiration = EXPIRATION;
    }
}
