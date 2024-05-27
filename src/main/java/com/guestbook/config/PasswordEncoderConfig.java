package com.guestbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
    //class that uses BCryptPasswordEncoder to encode the password
    //highly secure, based of Blowfish cypher algorithm
    //BCrypt automatically generates a random salt for each password
    //better than plain text storage, md5 or sha-1 which are
    // vulnerable at rainbow table/collision attacks
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
