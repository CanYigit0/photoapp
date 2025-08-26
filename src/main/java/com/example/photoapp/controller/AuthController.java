package com.example.photoapp.controller;

import com.example.photoapp.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Kullanıcı kaydı
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        String result = authenticationService.register(username, password);
        if (result.equals("Username already taken")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    // Kullanıcı girişi
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String token = authenticationService.login(username, password);
        if (token == null) {
            return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre");
        }
        return ResponseEntity.ok(token);
    }
}
