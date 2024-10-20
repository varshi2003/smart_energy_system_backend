package com.trustrace.smart_energy_backend.testing.service;



import com.trustrace.smart_energy_backend.model.SmartMeter;
import com.trustrace.smart_energy_backend.repository.SmartMeterRepository;
import com.trustrace.smart_energy_backend.service.SmartMeterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SmartMeterServiceTest {

    @InjectMocks
    private SmartMeterService smartMeterService;

    @Mock
    private SmartMeterRepository smartMeterRepository;

    public SmartMeterServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSmartMeters() {
        // Arrange
        List<SmartMeter> mockMeters = new ArrayList<>();
        mockMeters.add(new SmartMeter("1", "meter1", "active", "location1", Instant.now(), null, null));
        when(smartMeterRepository.findAll()).thenReturn(mockMeters);

        // Act
        List<SmartMeter> meters = smartMeterService.getAllSmartMeters();

        // Assert
        assertEquals(1, meters.size());
        assertEquals("meter1", meters.get(0).getSmartMeterId());
    }

    @Test
    public void testAddSmartMeter() {
        // Arrange
        SmartMeter newMeter = new SmartMeter(null, "meter2", "active", "location2", null, null, null);
        when(smartMeterRepository.save(newMeter)).thenReturn(newMeter);

        // Act
        SmartMeter addedMeter = smartMeterService.addSmartMeter(newMeter);

        // Assert
        assertEquals("meter2", addedMeter.getSmartMeterId());
    }
}
