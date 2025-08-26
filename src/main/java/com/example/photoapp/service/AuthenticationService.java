package com.example.photoapp.service;

import com.example.photoapp.entity.User;
import com.example.photoapp.repository.UserRepository;
import com.example.photoapp.util.JWTUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Kullanıcı kaydı
    public String register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "Username already taken";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // şifre encode ediliyor
        userRepository.save(user);
        return "User registered successfully";
    }

    // Login ve JWT üretimi
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Kullanıcı doğrulandı → JWT üret
            return jwtUtil.generateToken(username);
        }

        return null; // Hatalı giriş
    }

    // JWT doğrulama (opsiyonel ama başka servisler çağırabilir)
    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
