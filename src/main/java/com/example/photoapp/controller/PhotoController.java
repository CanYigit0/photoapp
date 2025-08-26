package com.example.photoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "JWT doğrulandı, erişim başarılı!";
    }
}
