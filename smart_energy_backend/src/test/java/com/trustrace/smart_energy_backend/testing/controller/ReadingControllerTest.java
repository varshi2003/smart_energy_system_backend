package com.trustrace.smart_energy_backend.testing.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.trustrace.smart_energy_backend.controller.ReadingController;
import com.trustrace.smart_energy_backend.model.Reading;
import com.trustrace.smart_energy_backend.service.ReadingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ReadingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReadingService readingService;

    @InjectMocks
    private ReadingController readingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "END_USER") // Simulate an authenticated user with ROLE_END_USER
    public void testGetReadingsBySmartMeterId() throws Exception {
        String smartMeterId = "123";
        Reading reading = new Reading();
        reading.setSmartMeterId(smartMeterId);
        List<Reading> mockReadings = Collections.singletonList(reading);

        when(readingService.getReadingsBySmartMeterId(smartMeterId)).thenReturn(mockReadings);

        mockMvc.perform(get("/api/readings/{smartMeterId}", smartMeterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].smartMeterId").value(smartMeterId));
    }

    @Test
    @WithMockUser(roles = "END_USER") // Simulate an authenticated user with ROLE_END_USER
    public void testAddReading() throws Exception {
        Reading reading = new Reading();
        reading.setSmartMeterId("123");

        when(readingService.addReading(any(Reading.class))).thenReturn(reading);

        mockMvc.perform(post("/api/readings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reading)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smartMeterId").value("123"));
    }
}
