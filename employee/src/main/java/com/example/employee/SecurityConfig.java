package com.example.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for POST testing; in production, configure properly
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()  // Require authentication for all requests
            )
            .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic Authentication

        return http.build();
    }
}
