package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.UserPreference;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for UserPreference entity.
 */
public interface UserPreferenceRepository extends MongoRepository<UserPreference, String> {
    Optional<UserPreference> findByUserId(String userId); // Find user preferences by User ID
}
