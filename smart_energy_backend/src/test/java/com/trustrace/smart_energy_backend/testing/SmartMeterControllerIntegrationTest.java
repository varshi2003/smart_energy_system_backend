package com.trustrace.smart_energy_backend.testing;



import com.trustrace.smart_energy_backend.controller.SmartMeterController;
import com.trustrace.smart_energy_backend.service.SmartMeterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SmartMeterController.class)
public class SmartMeterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SmartMeterService smartMeterService;

    @Test
    @WithMockUser(roles = "END_USER")
    public void testGetAllSmartMetersIntegration() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/smart-meters"))
                .andExpect(status().isOk());
    }
}
