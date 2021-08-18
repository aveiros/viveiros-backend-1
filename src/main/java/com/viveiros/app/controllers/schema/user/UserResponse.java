package com.viveiros.app.controllers.schema.user;

public class UserResponse {
    private UserError error;
    private String email;

    /**
     * Get the error
     *
     * @return the error
     */
    public UserError getError() {
        return error;
    }

    /**
     * Set the error
     *
     * @param error the error
     */
    public void setError(UserError error) {
        this.error = error;
    }

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
}
