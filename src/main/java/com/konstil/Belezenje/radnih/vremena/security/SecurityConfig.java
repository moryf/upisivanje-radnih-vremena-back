package com.konstil.Belezenje.radnih.vremena.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // Enable CORS
                .csrf(csrf -> csrf.disable())// Disable CSRF
                .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers("zaposleni/cardLogin/*").permitAll() // Allow all requests to the login and register endpoints
                                        .anyRequest().authenticated()

                        // Require authentication for all requests
                )// Require authentication for all requests
                .httpBasic(Customizer.withDefaults()); // Use HTTP Basic Authentication
        return http.build();
    }
}
