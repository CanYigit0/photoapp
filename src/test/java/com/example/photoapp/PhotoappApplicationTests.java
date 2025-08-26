package com.example.photoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Test profili kullan
class PhotoappApplicationTests {

    @Test
    void contextLoads() {
        // Basit context yükleme testi
    }
}
