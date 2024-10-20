package com.trustrace.smart_energy_backend.testing.service;



import com.trustrace.smart_energy_backend.model.Alert;
import com.trustrace.smart_energy_backend.repository.AlertRepository;
import com.trustrace.smart_energy_backend.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlertServiceTest {

    @InjectMocks
    private AlertService alertService;

    @Mock
    private AlertRepository alertRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAlertsBySmartMeterId() {
        // Arrange
        String smartMeterId = "meter123";
        Alert alert1 = new Alert(); // Assuming you have a default constructor
        alert1.setSmartMeterId(smartMeterId);
        List<Alert> alerts = new ArrayList<>();
        alerts.add(alert1);

        when(alertRepository.findBySmartMeterId(smartMeterId)).thenReturn(alerts);

        // Act
        List<Alert> result = alertService.getAlertsBySmartMeterId(smartMeterId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(smartMeterId, result.get(0).getSmartMeterId());
        verify(alertRepository, times(1)).findBySmartMeterId(smartMeterId);
    }

    @Test
    void testAddAlert() {
        // Arrange
        Alert alert = new Alert();
        alert.setSmartMeterId("meter123");
        when(alertRepository.save(any(Alert.class))).thenReturn(alert);

        // Act
        Alert result = alertService.addAlert(alert);

        // Assert
        assertEquals(alert.getSmartMeterId(), result.getSmartMeterId());
        verify(alertRepository, times(1)).save(any(Alert.class));
    }
}
