package com.trustrace.smart_energy_backend.testing.controller;



import com.trustrace.smart_energy_backend.controller.SmartMeterController;
import com.trustrace.smart_energy_backend.model.SmartMeter;
import com.trustrace.smart_energy_backend.service.SmartMeterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SmartMeterControllerTest {

    @InjectMocks
    private SmartMeterController smartMeterController;

    @Mock
    private SmartMeterService smartMeterService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(smartMeterController).build();
    }

    @Test
    @WithMockUser(roles = "END_USER")
    public void testGetAllSmartMeters() throws Exception {
        // Arrange
        List<SmartMeter> mockMeters = new ArrayList<>();
        mockMeters.add(new SmartMeter("1", "meter1", "active", "location1", Instant.now(), null, null));
        when(smartMeterService.getAllSmartMeters()).thenReturn(mockMeters);

        // Act & Assert
        mockMvc.perform(get("/api/smart-meters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].smartMeterId").value("meter1"));
    }

    @Test
    @WithMockUser(roles = "END_USER")
    public void testAddSmartMeter() throws Exception {
        // Arrange
        SmartMeter newMeter = new SmartMeter(null, "meter2", "active", "location2", null, null, null);
        when(smartMeterService.addSmartMeter(any(SmartMeter.class))).thenReturn(newMeter);

        // Act & Assert
        mockMvc.perform(post("/api/smart-meters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"smartMeterId\":\"meter2\",\"status\":\"active\",\"location\":\"location2\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smartMeterId").value("meter2"));
    }
}
