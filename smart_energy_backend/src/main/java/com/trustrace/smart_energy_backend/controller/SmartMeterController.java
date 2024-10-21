//package com.trustrace.smart_energy_backend.controller;
//
//import com.trustrace.smart_energy_backend.model.SmartMeter;
//import com.trustrace.smart_energy_backend.service.SmartMeterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/smart-meters")
//public class SmartMeterController {
//
//    @Autowired
//    private SmartMeterService smartMeterService;
//
//    /**
//     * Retrieves all smart meters (only accessible by END_USER).
//     *
//     * @return List of smart meters.
//     */
//    @GetMapping
//    @PreAuthorize("hasRole('ROLE_END_USER')")
//    public ResponseEntity<List<SmartMeter>> getAllSmartMeters() {
//        List<SmartMeter> meters = smartMeterService.getAllSmartMeters();
//        return ResponseEntity.ok(meters); // Returning response as JSON
//    }
//
//    /**
//     * Adds a new smart meter (only accessible by END_USER).
//     *
//     * @param smartMeter The smart meter to be added.
//     * @return The added smart meter.
//     */
//    @PostMapping
//    @PreAuthorize("hasRole('END_USER')")
//    public ResponseEntity<SmartMeter> addSmartMeter(@RequestBody SmartMeter smartMeter) {
//        SmartMeter addedMeter = smartMeterService.addSmartMeter(smartMeter);
//        return ResponseEntity.ok(addedMeter); // Returning response as JSON
//    }
//}

package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.SmartMeter;
import com.trustrace.smart_energy_backend.service.SmartMeterService;
import org.slf4j.Logger;  // Importing Logger
import org.slf4j.LoggerFactory;  // Importing LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated; // Import for validation
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Import for valid annotation
import java.util.List;

@RestController
@RequestMapping("/api/smart-meters")
@Validated  // Enable validation for the controller
public class SmartMeterController {

    private static final Logger logger = LoggerFactory.getLogger(SmartMeterController.class); // Logger instance

    @Autowired
    private SmartMeterService smartMeterService;

    /**
     * Retrieves all smart meters with pagination (only accessible by END_USER).
     *
     * @param page The page number to retrieve.
     * @param size The number of smart meters per page.
     * @return List of smart meters.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<SmartMeter>> getAllSmartMeters(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching smart meters - Page: {}, Size: {}", page, size); // Log the request
        List<SmartMeter> meters = smartMeterService.getAllSmartMeters(page, size);
        return ResponseEntity.ok(meters); // Returning response as JSON
    }

    /**
     * Adds a new smart meter (only accessible by END_USER).
     *
     * @param smartMeter The smart meter to be added.
     * @return The added smart meter.
     */
    @PostMapping
    @PreAuthorize("hasRole('END_USER')")
    public ResponseEntity<SmartMeter> addSmartMeter(@Valid @RequestBody SmartMeter smartMeter) { // Validate the request body
        logger.info("Adding new smart meter: {}", smartMeter); // Log the action
        SmartMeter addedMeter = smartMeterService.addSmartMeter(smartMeter);
        return ResponseEntity.ok(addedMeter); // Returning response as JSON
    }
}
