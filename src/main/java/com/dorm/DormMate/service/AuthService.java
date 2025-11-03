package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.User;
import com.dorm.DormMate.entity.dto.AuthResponse;
import com.dorm.DormMate.entity.dto.LoginRequest;
import com.dorm.DormMate.entity.dto.RegisterRequest;
import com.dorm.DormMate.repository.UserRepository;
import com.dorm.DormMate.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole().toUpperCase() : "STUDENT");
        user.setPhoneNumber(request.getPhoneNumber());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        
        userRepository.save(user);

        var jwtToken = jwtUtils.generateToken(user.getEmail());
        return AuthResponse.builder().token(jwtToken).build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // If authentication is successful, generate token
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found after authentication"));

        var jwtToken = jwtUtils.generateToken(user.getEmail());
        return AuthResponse.builder().token(jwtToken).build();
    }
}