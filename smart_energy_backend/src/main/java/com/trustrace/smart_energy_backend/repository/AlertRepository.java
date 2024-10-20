package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for Alert entity.
 */
public interface AlertRepository extends MongoRepository<Alert, String> {
    List<Alert> findBySmartMeterId(String smartMeterId); // Find alerts by SmartMeter ID
}
