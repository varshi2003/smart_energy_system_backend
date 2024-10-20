package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.repository.EnergyProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class EnergyProviderService {

    @Autowired
    private EnergyProviderRepository energyProviderRepository;

    /**
     * Retrieves all energy providers.
     *
     * @return List of energy providers.
     */
    public List<EnergyProvider> getAllEnergyProviders() {
        return energyProviderRepository.findAll();
    }

    /**
     * Adds a new energy provider.
     *
     * @param provider The energy provider to be added.
     * @return The added energy provider.
     */
    public EnergyProvider addEnergyProvider(EnergyProvider provider) {
        provider.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return energyProviderRepository.save(provider);
    }
}
