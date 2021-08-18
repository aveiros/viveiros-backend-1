package com.viveiros.app.controllers;

import com.viveiros.app.controllers.schema.login.LoginRequest;
import com.viveiros.app.controllers.schema.login.LoginResponse;
import com.viveiros.app.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authentication Controller
 */
@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    /**
     * Constructor for the controller
     *
     * @param service the authentication service
     */
    @Autowired
    public AuthenticationController(final AuthenticationService service) {
        this.service = service;
    }

    /**
     * Endpoint for user login
     *
     * @param body    the request body
     * @param request the request object
     * @return a response entity with a login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(final @RequestBody LoginRequest body, final HttpServletRequest request) {
        return this.service.handleLogin(body.getEmail(), body.getPassword(), request);
    }

    /**
     * Endpoint for user logout
     *
     * @param request  the request object
     * @param response the response object
     * @return a void response entity
     */
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletRequest request, final HttpServletResponse response) {
        return this.service.handleLogout(request, response);
    }
}
