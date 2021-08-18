package com.viveiros.app.services;

import com.viveiros.app.controllers.helpers.CookieConstants;
import com.viveiros.app.controllers.schema.login.LoginError;
import com.viveiros.app.controllers.schema.login.LoginResponse;
import com.viveiros.app.dal.UserDao;
import com.viveiros.app.dal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Authentication Service
 */
@Service
public class AuthenticationService {
    private final UserDao userDao;
    private final PasswordService passwordService;

    /**
     * Constructor for the service
     *
     * @param passwordService the password service
     * @param userDao         the user dao
     */
    @Autowired
    public AuthenticationService(final PasswordService passwordService, final UserDao userDao) {
        this.passwordService = passwordService;
        this.userDao = userDao;
    }

    /**
     * Handler for the user login endpoint
     *
     * @param email    the email
     * @param password the password
     * @param request  the request object
     * @return a response entity with a login response
     */
    public ResponseEntity<LoginResponse> handleLogin(final String email, final String password, final HttpServletRequest request) {
        if (!StringUtils.hasLength(email) || !StringUtils.hasLength(password)) {
            return ResponseEntity.badRequest().body(this.buildLoginResponse(LoginError.INVALID_PARAMETERS));
        }

        final User user = this.userDao.findByEmail(email);
        if (user == null) {
            return ResponseEntity.ok(this.buildLoginResponse(LoginError.USER_NOT_FOUND));
        }

        final boolean match = this.passwordService.matchesPassword(password, user.getPassword());
        if (!match) {
            return ResponseEntity.ok(this.buildLoginResponse(LoginError.PASSWORD_INCORRECT));
        }

        final HttpSession session = request.getSession(true);
        session.setAttribute(CookieConstants.ATTRIBUTE_EMAIL, user.getEmail());

        return ResponseEntity.ok(this.buildLoginResponse(null));
    }

    /**
     * Handler for the user logout endpoint
     *
     * @param request  the request object
     * @param response the response object
     * @return a response entity with a login response
     */
    public ResponseEntity<Void> handleLogout(final HttpServletRequest request, final HttpServletResponse response) {
        // clear request cookie (if exists)
        this.clearExistingCookie(request);

        // force set cookie on the response
        final Cookie cookie = new Cookie(CookieConstants.NAME, null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    /**
     * Helper method to clear an existing cookie (if exists)
     *
     * @param request the request object
     */
    private void clearExistingCookie(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(CookieConstants.ATTRIBUTE_EMAIL);
        }
    }

    /**
     * Helper method to build a login response
     *
     * @param error the error
     * @return a login response
     */
    private LoginResponse buildLoginResponse(LoginError error) {
        LoginResponse response = new LoginResponse();
        response.setError(error);

        return response;
    }
}
