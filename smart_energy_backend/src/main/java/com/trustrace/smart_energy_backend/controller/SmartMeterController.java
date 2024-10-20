package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.SmartMeter;
import com.trustrace.smart_energy_backend.service.SmartMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smart-meters")
public class SmartMeterController {

    @Autowired
    private SmartMeterService smartMeterService;

    /**
     * Retrieves all smart meters (only accessible by END_USER).
     *
     * @return List of smart meters.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<SmartMeter>> getAllSmartMeters() {
        List<SmartMeter> meters = smartMeterService.getAllSmartMeters();
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
    public ResponseEntity<SmartMeter> addSmartMeter(@RequestBody SmartMeter smartMeter) {
        SmartMeter addedMeter = smartMeterService.addSmartMeter(smartMeter);
        return ResponseEntity.ok(addedMeter); // Returning response as JSON
    }
}
