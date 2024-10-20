package com.trustrace.smart_energy_backend.testing.controller;



import com.trustrace.smart_energy_backend.controller.EnergyproviderController;
import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.service.EnergyProviderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnergyProviderControllerTest {

    @Mock
    private EnergyProviderService energyProviderService;

    @InjectMocks
    private EnergyproviderController energyProviderController;

    public EnergyProviderControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEnergyProviders() {
        EnergyProvider provider = new EnergyProvider(); // Initialize as needed
        when(energyProviderService.getAllEnergyProviders()).thenReturn(Collections.singletonList(provider));

        ResponseEntity<List<EnergyProvider>> response = energyProviderController.getAllEnergyProviders();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(energyProviderService, times(1)).getAllEnergyProviders();
    }

    @Test
    public void testAddEnergyProvider() {
        EnergyProvider provider = new EnergyProvider(); // Initialize as needed
        when(energyProviderService.addEnergyProvider(any(EnergyProvider.class))).thenReturn(provider);

        ResponseEntity<EnergyProvider> response = energyProviderController.addEnergyProvider(provider);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(provider, response.getBody());
        verify(energyProviderService, times(1)).addEnergyProvider(provider);
    }
}
