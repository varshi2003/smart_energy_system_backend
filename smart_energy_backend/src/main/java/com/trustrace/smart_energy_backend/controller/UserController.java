package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.User;
import com.trustrace.smart_energy_backend.repository.UserRepository;
import com.trustrace.smart_energy_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        // Default role to USER if not provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList("END_USER"));
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }
}
