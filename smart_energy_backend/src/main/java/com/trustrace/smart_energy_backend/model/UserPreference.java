package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents user preferences such as preferred energy provider.
 */
@Document(collection = "user_preferences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {
    @Id
    private String id; // Using String for MongoDB

    private String userId; // Reference to User
    private String currentProviderId; // Current provider
    private String preferredProviderId; // Preferred provider
    private Date createdAt; // Creation timestamp
    private Date updatedAt; // Update timestamp
}
