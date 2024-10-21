package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents an energy provider.
 */
@Document(collection = "energy_providers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyProvider {
    @Id
    private String id; // Using String for MongoDB

    private String name; // Name of the energy provider
    private Float ratePerKw; // Rate per kilowatt
    private String status; // Status of the provider
    private Date createdAt; // Creation timestamp
}
