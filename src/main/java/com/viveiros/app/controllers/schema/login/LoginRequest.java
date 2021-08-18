package com.viveiros.app.controllers.schema.login;

/**
 * Models a Login Request
 */
public class LoginRequest {
    private String email;
    private String password;

    /**
     * Get the email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
