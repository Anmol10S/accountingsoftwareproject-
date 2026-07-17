package com.anmol.accountingsoftware.config;

import com.anmol.accountingsoftware.auth.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(

            JwtAuthenticationFilter jwtFilter

    ) {

        this.jwtFilter = jwtFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(

            HttpSecurity http

    ) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(

                                "/auth/**"

                        ).permitAll()

                        .anyRequest()

                        .authenticated()

                )

                .sessionManagement(session ->

                        session.sessionCreationPolicy(

                                SessionCreationPolicy.STATELESS

                        )

                )

                .addFilterBefore(

                        jwtFilter,

                        UsernamePasswordAuthenticationFilter.class

                );

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(

            AuthenticationConfiguration configuration

    ) throws Exception {

        return configuration.getAuthenticationManager();

    }

}