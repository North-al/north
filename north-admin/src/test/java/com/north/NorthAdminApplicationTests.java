package com.north;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class NorthAdminApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void generateBcryptHashFor123456() {
        String hash = passwordEncoder.encode("123456");

        System.out.println("BCrypt hash: " + hash);
//        assertTrue(passwordEncoder.matches("123456", hash));
    }

}
