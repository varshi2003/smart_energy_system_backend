package com.trustrace.smart_energy_backend.testing.service;



import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.repository.EnergyProviderRepository;
import com.trustrace.smart_energy_backend.service.EnergyProviderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnergyProviderServiceTest {

    @Mock
    private EnergyProviderRepository energyProviderRepository;

    @InjectMocks
    private EnergyProviderService energyProviderService;

    public EnergyProviderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEnergyProviders() {
        EnergyProvider provider = new EnergyProvider(); // Initialize as needed
        when(energyProviderRepository.findAll()).thenReturn(Collections.singletonList(provider));

        List<EnergyProvider> result = energyProviderService.getAllEnergyProviders();

        assertEquals(1, result.size());
        verify(energyProviderRepository, times(1)).findAll();
    }

    @Test
    public void testAddEnergyProvider() {
        EnergyProvider provider = new EnergyProvider(); // Initialize with required fields
        provider.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        when(energyProviderRepository.save(any(EnergyProvider.class))).thenReturn(provider);

        EnergyProvider result = energyProviderService.addEnergyProvider(provider);

        assertEquals(provider.getCreatedAt(), result.getCreatedAt());
        verify(energyProviderRepository, times(1)).save(provider);
    }
}
