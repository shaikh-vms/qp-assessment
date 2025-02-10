package com.assessment.questionpro.grocery.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assessment.questionpro.grocery.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // User registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.createUser(username, password, role);
        return ResponseEntity.ok("User created successfully");
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        // Normally, you'd check credentials and return a token
        return ResponseEntity.ok("Logged in successfully");
    }
}
