package com.example.photoapp.service;

import com.example.photoapp.entity.User;
import com.example.photoapp.repository.UserRepository;
import com.example.photoapp.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Kullanıcı kaydı
    public String register(String email, String password) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email already exists");
        }
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        userRepository.save(user);

        return jwtUtil.generateToken(email);
    }

    // Kullanıcı girişi
    public String login(String email, String password) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new Exception("Invalid credentials");
        }
        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        return jwtUtil.generateToken(email);
    }
}
