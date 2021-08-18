package com.viveiros.app.services;

import com.viveiros.app.controllers.helpers.CookieConstants;
import com.viveiros.app.controllers.schema.user.UserError;
import com.viveiros.app.controllers.schema.user.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * User Service
 */
@Service
public class UserService {
    /**
     * Handler for the get current user information endpoint
     *
     * @param request the request object
     * @return a response entity with a user response
     */
    public ResponseEntity<UserResponse> handlerUserMe(final HttpServletRequest request) {
        final Optional<String> userOpt = this.extractUserFromRequestCookie(request);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(this.buildUserResponse(UserError.UNAUTHENTICATED, null));
        }

        return ResponseEntity.ok(this.buildUserResponse(null, userOpt.get()));
    }

    /**
     * Attempts to extract a email from the request cookie
     *
     * @param request the request object
     * @return a optional with the email (if exists), an empty optional otherwise
     */
    private Optional<String> extractUserFromRequestCookie(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        if (session == null) {
            return Optional.empty();
        }

        return Optional.ofNullable((String) session.getAttribute(CookieConstants.ATTRIBUTE_EMAIL));
    }

    /**
     * Helper method to build a user response
     *
     * @param error the error
     * @param email the email
     * @return a user response
     */
    private UserResponse buildUserResponse(final UserError error, final String email) {
        final UserResponse response = new UserResponse();
        response.setError(error);
        response.setEmail(email);
        return response;
    }
}
