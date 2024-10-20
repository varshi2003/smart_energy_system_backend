package com.trustrace.smart_energy_backend.testing.integrationtests;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.repository.EnergyProviderRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class EnergyProviderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EnergyProviderRepository energyProviderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        energyProviderRepository.deleteAll(); // Clean the repository before each test
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllEnergyProviders() throws Exception {
        mockMvc.perform(get("/api/energy-providers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAddEnergyProvider() throws Exception {
        EnergyProvider provider = new EnergyProvider(); // Initialize as needed

        mockMvc.perform(post("/api/energy-providers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(provider)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllEnergyProvidersUnauthorized() throws Exception {
        mockMvc.perform(get("/api/energy-providers"))
                .andExpect(status().isForbidden());
    }
}
