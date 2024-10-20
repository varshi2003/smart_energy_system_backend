package com.trustrace.smart_energy_backend.controller;

import com.trustrace.smart_energy_backend.model.Bill;
import com.trustrace.smart_energy_backend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * Retrieves all bills for a specific user (END_USER access).
     *
     * @param userId The ID of the user.
     * @return List of bills.
     */
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<List<Bill>> getBillsByUserId(@PathVariable String userId) {
        List<Bill> bills = billService.getBillsByUserId(userId);
        return ResponseEntity.ok(bills);
    }

    /**
     * Adds a new bill (ADMIN access only).
     *
     * @param bill The bill to be added.
     * @return The added bill.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_END_USER')")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill addedBill = billService.addBill(bill);
        return ResponseEntity.ok(addedBill);
    }
}
