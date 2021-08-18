package com.viveiros.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web Controller to render proper routes
 */
@Controller
public class WebController {
    /**
     * Get the index route
     *
     * @return the index template name
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Get the members route
     *
     * @return the members template name
     */
    @GetMapping("/members")
    public String members() {
        return "members";
    }
}
