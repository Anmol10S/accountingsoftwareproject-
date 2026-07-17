package com.anmol.accountingsoftware.auth.service;

import com.anmol.accountingsoftware.auth.dto.*;
import com.anmol.accountingsoftware.auth.entity.UserEntity;
import com.anmol.accountingsoftware.auth.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(

            UserRepository userRepository,

            BCryptPasswordEncoder passwordEncoder

    ) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

    }

    public RegisterResponseDTO register(

            RegisterRequestDTO request

    ) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(

                    "Email already exists."

            );

        }

        UserEntity user = new UserEntity();

        user.setName(

                request.getName()

        );

        user.setEmail(

                request.getEmail()

        );

        user.setPassword(

                passwordEncoder.encode(

                        request.getPassword()

                )

        );

        user.setRole(

                request.getRole()

        );

        user.setCreatedBy("SYSTEM");

        user.setUpdatedBy("SYSTEM");

        UserEntity savedUser =

                userRepository.save(user);

        RegisterResponseDTO response =
                new RegisterResponseDTO();

        response.setMessage(

                "User registered successfully."

        );

        response.setUserId(

                savedUser.getId()

        );

        response.setName(

                savedUser.getName()

        );

        response.setEmail(

                savedUser.getEmail()

        );

        return response;

    }

    public LoginResponseDTO login(

            LoginRequestDTO request

    ) {

        UserEntity user =

                userRepository

                        .findByEmail(

                                request.getEmail()

                        )

                        .orElseThrow(

                                () -> new RuntimeException(

                                        "Invalid email."

                                )

                        );

        if (

                !passwordEncoder.matches(

                        request.getPassword(),

                        user.getPassword()

                )

        ) {

            throw new RuntimeException(

                    "Invalid password."

            );

        }

        LoginResponseDTO response =
                new LoginResponseDTO();

        response.setAuthenticated(true);

        response.setMessage(

                "Login successful."

        );

        response.setName(

                user.getName()

        );

        response.setEmail(

                user.getEmail()

        );

        response.setRole(

                user.getRole().name()

        );

        return response;

    }

}