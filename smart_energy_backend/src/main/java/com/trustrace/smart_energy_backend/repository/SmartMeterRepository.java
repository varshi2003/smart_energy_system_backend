package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.SmartMeter;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for SmartMeter entity.
 */
public interface SmartMeterRepository extends MongoRepository<SmartMeter, String> {
}
