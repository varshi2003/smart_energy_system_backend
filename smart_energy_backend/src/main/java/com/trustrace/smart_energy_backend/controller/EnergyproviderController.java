package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.service.EnergyProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energy-providers")
public class EnergyproviderController {

    @Autowired
    private EnergyProviderService energyProviderService;

    /**
     * Retrieves all energy providers (ADMIN access only).
     *
     * @return List of energy providers.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<EnergyProvider>> getAllEnergyProviders() {
        List<EnergyProvider> providers = energyProviderService.getAllEnergyProviders();
        return ResponseEntity.ok(providers);
    }

    /**
     * Adds a new energy provider (ADMIN access only).
     *
     * @param provider The energy provider to be added.
     * @return The added energy provider.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<EnergyProvider> addEnergyProvider(@RequestBody EnergyProvider provider) {
        EnergyProvider addedProvider = energyProviderService.addEnergyProvider(provider);
        return ResponseEntity.ok(addedProvider);
    }
}
