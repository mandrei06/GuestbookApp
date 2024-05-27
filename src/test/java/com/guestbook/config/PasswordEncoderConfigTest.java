package com.guestbook.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderConfigTest {

    private PasswordEncoderConfig passwordEncoderConfig;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoderConfig = new PasswordEncoderConfig();
        passwordEncoder = passwordEncoderConfig.passwordEncoder();
    }

    @Test
    void passwordEncoder() {
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }

    @Test
    void passwordEncoderEncodingAndMatching() {
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        assertNotNull(encodedPassword, "Encoded password should not be null");
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "Encoded password should match raw password");
    }
}
