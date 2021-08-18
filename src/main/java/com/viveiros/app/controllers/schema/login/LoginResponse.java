package com.viveiros.app.controllers.schema.login;

/**
 * Models a Login Response
 */
public class LoginResponse {
    private LoginError error;

    /**
     * Get the error
     *
     * @return the error
     */
    public LoginError getError() {
        return error;
    }

    /**
     * Set the error
     *
     * @param error the error
     */
    public void setError(LoginError error) {
        this.error = error;
    }
}
