package com.viveiros.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Models App Properties
 */
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private int passwordEncoderStrength;

    public int getPasswordEncoderStrength() {
        return passwordEncoderStrength;
    }

    public void setPasswordEncoderStrength(int passwordEncoderStrength) {
        this.passwordEncoderStrength = passwordEncoderStrength;
    }
}
