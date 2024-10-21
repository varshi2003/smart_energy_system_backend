//package com.trustrace.smart_energy_backend.repository;
//
//import com.trustrace.smart_energy_backend.model.Alert;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
///**
// * Repository interface for Alert entity.
// */
//public interface AlertRepository extends MongoRepository<Alert, String> {
//    List<Alert> findBySmartMeterId(String smartMeterId); // Find alerts by SmartMeter ID
//}
package com.trustrace.smart_energy_backend.repository;

import com.trustrace.smart_energy_backend.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page; // Import for pagination
import org.springframework.data.domain.Pageable; // Import for Pageable

import java.util.List;

public interface AlertRepository extends MongoRepository<Alert, String> {

    // Existing method
    List<Alert> findBySmartMeterId(String smartMeterId);

    // New method for pagination
    Page<Alert> findBySmartMeterId(String smartMeterId, Pageable pageable); // Updated to accept Pageable
}
