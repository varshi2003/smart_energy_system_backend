package com.trustrace.smart_energy_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Represents a user's energy bill.
 */
@Document(collection = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    @Id
    private String id; // Using String for MongoDB

    private String userId; // Reference to User
    private Float totalUsage; // Total energy used
    private Float ratePerKw; // Rate per kilowatt
    private Float totalAmount; // Total bill amount
    private Date billingPeriodStart; // Start of billing period
    private Date billingPeriodEnd; // End of billing period
    private String status; // Status of the bill
    private Date createdAt; // Creation timestamp
}
