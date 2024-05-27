package com.guestbook.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    //class responsible for attempting to authenticate the user, is overridden the method authenticate from AuthenticationProvider

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        logger.debug("Authenticating user: {}", username);

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user == null) {
            logger.debug("User not found: {}", username);
            throw new BadCredentialsException("Username not found.");
        }

        logger.debug("User found: {}", username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.debug("Invalid password for user: {}", username);
            throw new BadCredentialsException("Wrong password.");
        }

        logger.debug("Authentication successful for user: {}", username);

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
