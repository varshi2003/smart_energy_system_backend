package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.EnergyProvider;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface for EnergyProvider entity.
 */
public interface EnergyProviderRepository extends MongoRepository<EnergyProvider, String> {
}
