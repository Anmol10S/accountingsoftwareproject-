package com.anmol.accountingsoftware.auth.controller;

import com.anmol.accountingsoftware.auth.dto.*;
import com.anmol.accountingsoftware.auth.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(

            AuthService authService

    ) {

        this.authService = authService;

    }

    @PostMapping("/register")
    public RegisterResponseDTO register(

            @RequestBody RegisterRequestDTO request

    ) {

        return authService.register(

                request

        );

    }

    @PostMapping("/login")
    public LoginResponseDTO login(

            @RequestBody LoginRequestDTO request

    ) {

        return authService.login(

                request

        );

    }

}