package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.UserPreference;
import com.trustrace.smart_energy_backend.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserPreferenceService {

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    /**
     * Retrieves user preferences by user ID.
     *
     * @param userId The ID of the user.
     * @return Optional of user preference.
     */
    public Optional<UserPreference> getUserPreferencesByUserId(String userId) {
        return userPreferenceRepository.findByUserId(userId);
    }

    /**
     * Adds or updates user preferences.
     *
     * @param userPreference The user preference to be saved.
     * @return The saved user preference.
     */
    public UserPreference saveUserPreference(UserPreference userPreference) {
        userPreference.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userPreference.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userPreferenceRepository.save(userPreference);
    }
}
