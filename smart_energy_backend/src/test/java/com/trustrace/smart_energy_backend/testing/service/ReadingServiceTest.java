package com.trustrace.smart_energy_backend.testing.service;



import com.trustrace.smart_energy_backend.model.Reading;
import com.trustrace.smart_energy_backend.repository.ReadingRepository;
import com.trustrace.smart_energy_backend.service.ReadingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReadingServiceTest {

    @Mock
    private ReadingRepository readingRepository;

    @InjectMocks
    private ReadingService readingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReadingsBySmartMeterId() {
        String smartMeterId = "123";
        Reading reading = new Reading();
        reading.setSmartMeterId(smartMeterId);
        List<Reading> mockReadings = Collections.singletonList(reading);

        when(readingRepository.findBySmartMeterId(smartMeterId)).thenReturn(mockReadings);

        List<Reading> readings = readingService.getReadingsBySmartMeterId(smartMeterId);

        assertEquals(1, readings.size());
        assertEquals(smartMeterId, readings.get(0).getSmartMeterId());
        verify(readingRepository, times(1)).findBySmartMeterId(smartMeterId);
    }

    @Test
    public void testAddReading() {
        Reading reading = new Reading();
        reading.setSmartMeterId("123");

        when(readingRepository.save(any(Reading.class))).thenReturn(reading);

        Reading addedReading = readingService.addReading(reading);

        assertEquals("123", addedReading.getSmartMeterId());
        verify(readingRepository, times(1)).save(any(Reading.class));
    }
}
