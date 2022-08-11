package com.codestates.section4week1.config.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    @Value("${jwt.secret}")
    private String key;

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProvider(key);
    }
}
