package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

/**
 * Represents a smart meter.
 */
@Document(collection = "smart_meters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartMeter {
    @Id
    private String id; // Using String for MongoDB

    private String smartMeterId; // Smart meter ID
    private String status; // Status of the meter (active, inactive, etc.)
    private String location; // Location of the meter
    private Instant createdAt; // Creation timestamp

    // Relationship with readings and alerts
    private List<Reading> readings; // List of meter readings
    private List<Alert> alerts; // List of alerts
}
