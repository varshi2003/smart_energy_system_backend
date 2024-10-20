package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.Reading;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for Reading entity.
 */
public interface ReadingRepository extends MongoRepository<Reading, String> {
    List<Reading> findBySmartMeterId(String smartMeterId); // Find readings by SmartMeter ID
}
