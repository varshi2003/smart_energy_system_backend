//package com.trustrace.smart_energy_backend.service;
//
//import com.trustrace.smart_energy_backend.model.EnergyProvider;
//import com.trustrace.smart_energy_backend.repository.EnergyProviderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Service
//public class EnergyProviderService {
//
//    @Autowired
//    private EnergyProviderRepository energyProviderRepository;
//
//    /**
//     * Retrieves all energy providers.
//     *
//     * @return List of energy providers.
//     */
//    public List<EnergyProvider> getAllEnergyProviders() {
//        return energyProviderRepository.findAll();
//    }
//
//    /**
//     * Adds a new energy provider.
//     *
//     * @param provider The energy provider to be added.
//     * @return The added energy provider.
//     */
//    public EnergyProvider addEnergyProvider(EnergyProvider provider) {
//        provider.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//        return energyProviderRepository.save(provider);
//    }
//}
package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.repository.EnergyProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyProviderService {

    @Autowired
    private EnergyProviderRepository energyProviderRepository;

    /**
     * Retrieves all energy providers with pagination.
     *
     * @param pageable The pagination information.
     * @return A paginated list of energy providers.
     */
    public Page<EnergyProvider> getAllEnergyProviders(Pageable pageable) {
        return energyProviderRepository.findAll(pageable);
    }

    /**
     * Retrieves an energy provider by ID.
     *
     * @param id The ID of the energy provider.
     * @return The energy provider if found, otherwise an empty Optional.
     */
    public Optional<EnergyProvider> getEnergyProviderById(String id) {
        return energyProviderRepository.findById(id);
    }

    /**
     * Adds a new energy provider.
     *
     * @param provider The energy provider to be added.
     * @return The added energy provider.
     */
    public EnergyProvider addEnergyProvider(EnergyProvider provider) {
        provider.setCreatedAt(new java.util.Date());  // Use Date for MongoDB compatibility
        return energyProviderRepository.save(provider);
    }

    /**
     * Updates an existing energy provider.
     *
     * @param id The ID of the energy provider to update.
     * @param updatedProvider The updated energy provider data.
     * @return The updated energy provider if found, otherwise an empty Optional.
     */
    public Optional<EnergyProvider> updateEnergyProvider(String id, EnergyProvider updatedProvider) {
        return energyProviderRepository.findById(id).map(existingProvider -> {
            existingProvider.setName(updatedProvider.getName());
            existingProvider.setRatePerKw(updatedProvider.getRatePerKw());
            existingProvider.setStatus(updatedProvider.getStatus());
            return energyProviderRepository.save(existingProvider);
        });
    }

    /**
     * Deletes an energy provider by ID.
     *
     * @param id The ID of the energy provider to delete.
     * @return True if the provider was deleted, otherwise false.
     */
    public boolean deleteEnergyProvider(String id) {
        if (energyProviderRepository.existsById(id)) {
            energyProviderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
