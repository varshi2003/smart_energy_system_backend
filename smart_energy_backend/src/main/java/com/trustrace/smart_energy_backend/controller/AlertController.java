//package com.trustrace.smart_energy_backend.controller;
//
//import com.trustrace.smart_energy_backend.model.Alert;
//import com.trustrace.smart_energy_backend.service.AlertService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/alerts")
//public class AlertController {
//
//    @Autowired
//    private AlertService alertService;
//
//    /**
//     * Retrieves all alerts for a specific smart meter (END_USER access).
//     *
//     * @param smartMeterId The ID of the smart meter.
//     * @return List of alerts.
//     */
//    @GetMapping("/{smartMeterId}")
//    @PreAuthorize("hasRole('ROLE_END_USER')")
//    public ResponseEntity<List<Alert>> getAlertsBySmartMeterId(@PathVariable String smartMeterId) {
//        List<Alert> alerts = alertService.getAlertsBySmartMeterId(smartMeterId);
//        return ResponseEntity.ok(alerts);
//    }
//
//    /**
//     * Adds a new alert (ADMIN access only).
//     *
//     * @param alert The alert to be added.
//     * @return The added alert.
//     */
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_END_USER')")
//    public ResponseEntity<Alert> addAlert(@RequestBody Alert alert) {
//        Alert addedAlert = alertService.addAlert(alert);
//        return ResponseEntity.ok(addedAlert);
//    }
//}
package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.Alert;
import com.trustrace.smart_energy_backend.service.AlertService;
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
@RequestMapping("/api/alerts")
@Validated  // Enable validation for the controller
public class AlertController {

    private static final Logger logger = LoggerFactory.getLogger(AlertController.class); // Logger instance

    @Autowired
    private AlertService alertService;

    /**
     * Retrieves all alerts for a specific smart meter with pagination (END_USER access).
     *
     * @param smartMeterId The ID of the smart meter.
     * @param page The page number to retrieve.
     * @param size The number of alerts per page.
     * @return List of alerts.
     */
    @GetMapping("/{smartMeterId}")
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<Alert>> getAlertsBySmartMeterId(
            @PathVariable String smartMeterId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Fetching alerts for smart meter ID: {}, Page: {}, Size: {}", smartMeterId, page, size); // Log the request
        List<Alert> alerts = alertService.getAlertsBySmartMeterId(smartMeterId, page, size);
        return ResponseEntity.ok(alerts);
    }

    /**
     * Adds a new alert (ADMIN access only).
     *
     * @param alert The alert to be added.
     * @return The added alert.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<Alert> addAlert(@Valid @RequestBody Alert alert) { // Validate the request body
        logger.info("Adding new alert: {}", alert); // Log the action
        Alert addedAlert = alertService.addAlert(alert);
        return ResponseEntity.ok(addedAlert);
    }
}
