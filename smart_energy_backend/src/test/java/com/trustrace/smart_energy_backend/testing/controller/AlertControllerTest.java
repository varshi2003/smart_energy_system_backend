package com.trustrace.smart_energy_backend.testing.controller;


import com.trustrace.smart_energy_backend.controller.AlertController;
import com.trustrace.smart_energy_backend.model.Alert;
import com.trustrace.smart_energy_backend.service.AlertService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class AlertControllerTest {

    @InjectMocks
    private AlertController alertController;

    @Mock
    private AlertService alertService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(alertController).build();
    }

    @Test
    @WithMockUser(roles = "END_USER") // Mock user with END_USER role
    void testGetAlertsBySmartMeterId() throws Exception {
        // Arrange
        String smartMeterId = "meter123";
        Alert alert = new Alert();
        alert.setSmartMeterId(smartMeterId);
        List<Alert> alerts = new ArrayList<>();
        alerts.add(alert);
        when(alertService.getAlertsBySmartMeterId(smartMeterId)).thenReturn(alerts);

        // Act & Assert
        mockMvc.perform(get("/api/alerts/{smartMeterId}", smartMeterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].smartMeterId").value(smartMeterId));
    }

    @Test
    @WithMockUser(roles = "END_USER") // Mock user with END_USER role
    void testAddAlert() throws Exception {
        // Arrange
        Alert alert = new Alert();
        alert.setSmartMeterId("meter123");
        when(alertService.addAlert(any(Alert.class))).thenReturn(alert);

        // Act & Assert
        mockMvc.perform(post("/api/alerts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"smartMeterId\":\"meter123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smartMeterId").value("meter123"));
    }
}
