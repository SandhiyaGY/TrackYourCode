package com.trackyourcode.service;

import com.trackyourcode.dto.LoginRequest;
import com.trackyourcode.dto.RegisterRequest;
import com.trackyourcode.entity.User;
import com.trackyourcode.repository.UserRepository;
import com.trackyourcode.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    // Register User
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // Login User
    public String login(LoginRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        boolean match = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!match) {
            return "Invalid Credentials";
        }

        // Generate JWT Token
        return jwtService.generateToken(user.getEmail());
    }
}