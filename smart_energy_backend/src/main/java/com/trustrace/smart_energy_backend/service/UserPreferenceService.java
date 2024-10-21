//package com.trustrace.smart_energy_backend.service;
//
//import com.trustrace.smart_energy_backend.model.UserPreference;
//import com.trustrace.smart_energy_backend.repository.UserPreferenceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.Optional;
//
//@Service
//public class UserPreferenceService {
//
//    @Autowired
//    private UserPreferenceRepository userPreferenceRepository;
//
//    /**
//     * Retrieves user preferences by user ID.
//     *
//     * @param userId The ID of the user.
//     * @return Optional of user preference.
//     */
//    public Optional<UserPreference> getUserPreferencesByUserId(String userId) {
//        return userPreferenceRepository.findByUserId(userId);
//    }
//
//    /**
//     * Adds or updates user preferences.
//     *
//     * @param userPreference The user preference to be saved.
//     * @return The saved user preference.
//     */
//    public UserPreference saveUserPreference(UserPreference userPreference) {
//        userPreference.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        userPreference.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//        return userPreferenceRepository.save(userPreference);
//    }
//}
package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.UserPreference;
import com.trustrace.smart_energy_backend.repository.UserPreferenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * Service for managing user preferences.
 */
@Service
public class UserPreferenceService {

    private static final Logger logger = LoggerFactory.getLogger(UserPreferenceService.class);

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    /**
     * Retrieves user preferences by user ID.
     *
     * @param userId The ID of the user.
     * @return Optional of user preference.
     */
    public Optional<UserPreference> getUserPreferencesByUserId(String userId) {
        logger.info("Retrieving user preferences for userId: {}", userId);
        return userPreferenceRepository.findByUserId(userId);
    }

    /**
     * Adds or updates user preferences.
     *
     * @param userPreference The user preference to be saved.
     * @return The saved user preference.
     */
    public UserPreference saveUserPreference(UserPreference userPreference) {
        logger.info("Saving user preference for userId: {}", userPreference.getUserId());
        userPreference.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userPreference.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return userPreferenceRepository.save(userPreference);
    }
}
