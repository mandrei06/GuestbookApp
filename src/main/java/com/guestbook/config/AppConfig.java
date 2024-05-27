package com.guestbook.config;

import com.guestbook.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    // class that defines/config a bean for authentication

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AppConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(userService, passwordEncoder);
    }
}
