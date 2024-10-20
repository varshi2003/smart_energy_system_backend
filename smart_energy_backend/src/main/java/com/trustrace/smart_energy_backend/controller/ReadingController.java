package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.Reading;
import com.trustrace.smart_energy_backend.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    /**
     * Retrieves all readings for a specific smart meter (END_USER access).
     *
     * @param smartMeterId The ID of the smart meter.
     * @return List of readings.
     */
    @GetMapping("/{smartMeterId}")
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<Reading>> getReadingsBySmartMeterId(@PathVariable String smartMeterId) {
        List<Reading> readings = readingService.getReadingsBySmartMeterId(smartMeterId);
        return ResponseEntity.ok(readings);
    }

    /**
     * Adds a new reading (ADMIN access only).
     *
     * @param reading The reading to be added.
     * @return The added reading.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<Reading> addReading(@RequestBody Reading reading) {
        Reading addedReading = readingService.addReading(reading);
        return ResponseEntity.ok(addedReading);
    }
}
