package com.example.photoapp.controller;

import com.example.photoapp.service.AuthenticationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request) throws Exception {
        String token = authService.register(request.getEmail(), request.getPassword());
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) throws Exception {
        String token = authService.login(request.getEmail(), request.getPassword());
        return new AuthResponse(token);
    }

    // DTO’lar
    @Data
    static class AuthRequest {
        private String email;
        private String password;
    }

    @Data
    static class AuthResponse {
        private String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }
}
