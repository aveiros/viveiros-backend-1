package com.viveiros.app.services;

import com.viveiros.app.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Password Service
 */
@Service
public class PasswordService {
    private final AppProperties properties;

    /**
     * Constructor for the service
     *
     * @param properties the properties
     */
    @Autowired
    public PasswordService(final AppProperties properties) {
        this.properties = properties;
    }

    /**
     * Checks whether two passwords (plan & hashed password) match
     *
     * @param password       the plain text password
     * @param hashedPassword the hashed password
     * @return true if they match, false otherwise
     */
    public boolean matchesPassword(final String password, final String hashedPassword) {
        final BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder(this.properties.getPasswordEncoderStrength(), new SecureRandom());
        return encoder.matches(password, hashedPassword);
    }
}
