package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents an alert generated by the system.
 */
@Document(collection = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {
    @Id
    private String id; // Using String for MongoDB

    private String smartMeterId; // Reference to SmartMeter
    private String message; // Alert message
    private String alertType; // Type of alert
    private String status; // Status of the alert
    private Date createdAt; // Creation timestamp
}