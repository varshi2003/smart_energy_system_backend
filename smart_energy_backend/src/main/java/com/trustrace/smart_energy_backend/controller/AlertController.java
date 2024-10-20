package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.Alert;
import com.trustrace.smart_energy_backend.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    /**
     * Retrieves all alerts for a specific smart meter (END_USER access).
     *
     * @param smartMeterId The ID of the smart meter.
     * @return List of alerts.
     */
    @GetMapping("/{smartMeterId}")
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<Alert>> getAlertsBySmartMeterId(@PathVariable String smartMeterId) {
        List<Alert> alerts = alertService.getAlertsBySmartMeterId(smartMeterId);
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
    public ResponseEntity<Alert> addAlert(@RequestBody Alert alert) {
        Alert addedAlert = alertService.addAlert(alert);
        return ResponseEntity.ok(addedAlert);
    }
}
