package com.trustrace.smart_energy_backend.service;

import com.trustrace.smart_energy_backend.model.Bill;
import com.trustrace.smart_energy_backend.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    /**
     * Retrieves all bills for a specific user.
     *
     * @param userId The ID of the user.
     * @return List of bills.
     */
    public List<Bill> getBillsByUserId(String userId) {
        return billRepository.findByUserId(userId);
    }

    /**
     * Adds a new bill.
     *
     * @param bill The bill to be added.
     * @return The added bill.
     */
    public Bill addBill(Bill bill) {
        bill.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return billRepository.save(bill);
    }
}
