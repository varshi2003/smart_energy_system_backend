//package com.trustrace.smart_energy_backend.service;
//
//import com.trustrace.smart_energy_backend.model.SmartMeter;
//import com.trustrace.smart_energy_backend.repository.SmartMeterRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.List;
//
//@Service
//public class SmartMeterService {
//
//    @Autowired
//    private SmartMeterRepository smartMeterRepository;
//
//    /**
//     * Retrieves all smart meters.
//     *
//     * @return List of smart meters.
//     */
//    public List<SmartMeter> getAllSmartMeters() {
//        return smartMeterRepository.findAll();
//    }
//
//    /**
//     * Adds a new smart meter.
//     *
//     * @param smartMeter The smart meter to be added.
//     * @return The added smart meter.
//     */
//    public SmartMeter addSmartMeter(SmartMeter smartMeter) {
//        // Setting the current time for createdAt
//        smartMeter.setCreatedAt(Instant.now());
//        return smartMeterRepository.save(smartMeter);
//    }
//}
//
package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.SmartMeter;
import com.trustrace.smart_energy_backend.repository.SmartMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // Import for pagination
import org.springframework.data.domain.PageRequest; // Import for PageRequest
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SmartMeterService {

    @Autowired
    private SmartMeterRepository smartMeterRepository;

    /**
     * Retrieves all smart meters with pagination.
     *
     * @param page The page number to retrieve.
     * @param size The number of smart meters per page.
     * @return List of smart meters.
     */
    public List<SmartMeter> getAllSmartMeters(int page, int size) {
        Page<SmartMeter> smartMeterPage = smartMeterRepository.findAll(PageRequest.of(page, size));
        return smartMeterPage.getContent(); // Return the content of the page
    }

    /**
     * Adds a new smart meter.
     *
     * @param smartMeter The smart meter to be added.
     * @return The added smart meter.
     */
    public SmartMeter addSmartMeter(SmartMeter smartMeter) {
        // Setting the current time for createdAt
        smartMeter.setCreatedAt(Instant.now());
        return smartMeterRepository.save(smartMeter);
    }
}
