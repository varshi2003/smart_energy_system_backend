//package com.trustrace.smart_energy_backend.controller;
//
//import com.trustrace.smart_energy_backend.model.EnergyProvider;
//import com.trustrace.smart_energy_backend.service.EnergyProviderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/energy-providers")
//public class EnergyproviderController {
//
//    @Autowired
//    private EnergyProviderService energyProviderService;
//
//    /**
//     * Retrieves all energy providers (ADMIN access only).
//     *
//     * @return List of energy providers.
//     */
//    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<List<EnergyProvider>> getAllEnergyProviders() {
//        List<EnergyProvider> providers = energyProviderService.getAllEnergyProviders();
//        return ResponseEntity.ok(providers);
//    }
//
//    /**
//     * Adds a new energy provider (ADMIN access only).
//     *
//     * @param provider The energy provider to be added.
//     * @return The added energy provider.
//     */
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<EnergyProvider> addEnergyProvider(@RequestBody EnergyProvider provider) {
//        EnergyProvider addedProvider = energyProviderService.addEnergyProvider(provider);
//        return ResponseEntity.ok(addedProvider);
//    }
//}
package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.EnergyProvider;
import com.trustrace.smart_energy_backend.service.EnergyProviderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing energy providers.
 * <p>
 * Only users with role 'ADMIN' have access to these operations.
 */
@RestController
@RequestMapping("/api/energy-providers")
public class EnergyproviderController {

    private static final Logger logger = LoggerFactory.getLogger(EnergyproviderController.class);

    @Autowired
    private EnergyProviderService energyProviderService;

    /**
     * Retrieves all energy providers with pagination (ADMIN access only).
     *
     * @param page the page number to retrieve (0-based).
     * @param size the number of records per page.
     * @return a paginated list of energy providers.
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<EnergyProvider>> getAllEnergyProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            // Pagination request object
            Pageable pageable = PageRequest.of(page, size);

            // Fetch paginated results
            Page<EnergyProvider> providers = energyProviderService.getAllEnergyProviders(pageable);

            // Logging the retrieved page details
            logger.info("Retrieved page {} of energy providers, size: {}", page, size);

            return ResponseEntity.ok(providers);

        } catch (Exception ex) {
            logger.error("Error retrieving energy providers", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Adds a new energy provider (ADMIN access only).
     *
     * @param provider The energy provider to be added. Fields must be valid.
     * @return The added energy provider, or an error if the request was invalid.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addEnergyProvider(@Valid @RequestBody EnergyProvider provider) {
        try {
            // Logging the new provider request
            logger.info("Attempting to add new energy provider: {}", provider.getName());

            // Add the energy provider to the database
            EnergyProvider addedProvider = energyProviderService.addEnergyProvider(provider);

            // Logging the success of adding the provider
            logger.info("Successfully added energy provider: {}", addedProvider.getId());

            return ResponseEntity.ok(addedProvider);

        } catch (Exception ex) {
            logger.error("Error adding energy provider", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the energy provider.");
        }
    }

}
