package com.trustrace.smart_energy_backend.testing.integrationtests;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "END_USER")
    void testGetAlertsBySmartMeterId() throws Exception {
        String smartMeterId = "meter123";

        mockMvc.perform(get("/api/alerts/{smartMeterId}", smartMeterId))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "END_USER")
    void testAddAlert() throws Exception {
        mockMvc.perform(post("/api/alerts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"smartMeterId\":\"meter123\"}"))
                .andExpect(status().isOk());
    }
}
