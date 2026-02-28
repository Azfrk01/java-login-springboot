package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
@RestController
public class LoginController {
    // Dummy user credentials
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "password12345";
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
            return "success";
        } else {
            return "failure";
        }
    }
}
