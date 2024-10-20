package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.Reading;
import com.trustrace.smart_energy_backend.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    /**
     * Retrieves all readings for a specific smart meter.
     *
     * @param smartMeterId The ID of the smart meter.
     * @return List of readings.
     */
    public List<Reading> getReadingsBySmartMeterId(String smartMeterId) {
        return readingRepository.findBySmartMeterId(smartMeterId);
    }

    /**
     * Adds a new reading.
     *
     * @param reading The reading to be added.
     * @return The added reading.
     */
    public Reading addReading(Reading reading) {
        reading.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return readingRepository.save(reading);
    }
}
