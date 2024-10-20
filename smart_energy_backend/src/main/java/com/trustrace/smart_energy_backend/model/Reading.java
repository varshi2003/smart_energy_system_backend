package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents an energy reading from a smart meter.
 */
@Document(collection = "readings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reading {
    @Id
    private String id; // Using String for MongoDB

    private String smartMeterId; // Reference to SmartMeter
    private Date timestamp; // When the reading was taken
    private Float readingValue; // The value of the reading
    private Date createdAt; // Creation timestamp
}
