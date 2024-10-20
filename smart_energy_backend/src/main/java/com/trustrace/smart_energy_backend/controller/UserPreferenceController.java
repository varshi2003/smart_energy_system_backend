package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.UserPreference;
import com.trustrace.smart_energy_backend.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-preferences")
public class UserPreferenceController {

    @Autowired
    private UserPreferenceService userPreferenceService;

    /**
     * Retrieves user preferences by user ID (END_USER access).
     *
     * @param userId The ID of the user.
     * @return User preferences.
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<Optional<UserPreference>> getUserPreferencesByUserId(@PathVariable String userId) {
        Optional<UserPreference> userPreferences = userPreferenceService.getUserPreferencesByUserId(userId);
        return ResponseEntity.ok(userPreferences);
    }

    /**
     * Adds or updates user preferences (END_USER access).
     *
     * @param userPreference The user preference to be saved.
     * @return The saved user preference.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<UserPreference> saveUserPreference(@RequestBody UserPreference userPreference) {
        UserPreference savedPreference = userPreferenceService.saveUserPreference(userPreference);
        return ResponseEntity.ok(savedPreference);
    }
}