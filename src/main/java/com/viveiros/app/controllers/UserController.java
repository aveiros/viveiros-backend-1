package com.viveiros.app.controllers;

import com.viveiros.app.controllers.schema.user.UserResponse;
import com.viveiros.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * User Controller
 */
@RestController
public class UserController {
    private final UserService service;

    /**
     * Constructor for the controller
     *
     * @param service the user service
     */
    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    /**
     * Endpoint for current user information
     *
     * @param request the request object
     * @return a response entity with a user response
     */
    @GetMapping("/user/me")
    public ResponseEntity<UserResponse> userMe(final HttpServletRequest request) {
        return this.service.handlerUserMe(request);
    }
}
