package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.Alert;
import com.trustrace.smart_energy_backend.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    /**
     * Retrieves all alerts for a specific smart meter.
     *
     * @param smartMeterId The ID of the smart meter.
     * @return List of alerts.
     */
    public List<Alert> getAlertsBySmartMeterId(String smartMeterId) {
        return alertRepository.findBySmartMeterId(smartMeterId);
    }

    /**
     * Adds a new alert.
     *
     * @param alert The alert to be added.
     * @return The added alert.
     */
    public Alert addAlert(Alert alert) {
        alert.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return alertRepository.save(alert);
    }
}
