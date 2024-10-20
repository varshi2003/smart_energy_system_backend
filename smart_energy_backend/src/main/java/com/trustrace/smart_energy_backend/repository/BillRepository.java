package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository interface for Bill entity.
 */
public interface BillRepository extends MongoRepository<Bill, String> {
    List<Bill> findByUserId(String userId); // Find bills by User ID
}
