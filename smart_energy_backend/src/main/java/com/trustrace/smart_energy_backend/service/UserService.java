package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.User;
import com.trustrace.smart_energy_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Set the creation timestamp
        user.setCreatedAt(LocalDateTime.now());

        // Encode the password before saving the user
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }

    // Method to fetch users by role
    public List<User> findUsersByRole(String role) {
        return userRepository.findByRoles(role);
    }
}
