package com.examly.springapp.service;

import com.examly.springapp.entity.repairshop;
import com.examly.springapp.repository.repairshoprepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class repairshopserv {

    @Autowired
    private repairshoprepo repairShopRepo;

    // Create a new repair shop
    public repairshop createRepairShop(repairshop repairShop) {
        return repairShopRepo.save(repairShop);
    }

    // Get a repair shop by ID
    public repairshop getRepairShopById(Long id) { // Change int to Long
        Optional<repairshop> repairShop = repairShopRepo.findById(id);
        return repairShop.orElse(null);
    }

    // Get all repair shops
    public List<repairshop> getAllRepairShops() {
        return repairShopRepo.findAll();
    }

    // Update an existing repair shop
    public repairshop updateRepairShop(Long id, repairshop updatedShop) { // Change int to Long
        if (repairShopRepo.existsById(id)) {
            updatedShop.setId(id); // Ensure the ID stays the same
            return repairShopRepo.save(updatedShop);
        }
        return null;
    }

    // Delete a repair shop
    public boolean deleteRepairShop(Long id) { // Change int to Long
        if (repairShopRepo.existsById(id)) {
            repairShopRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public repairshop getRepairShopById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRepairShopById'");
    }

    public repairshop updateRepairShop(int id, repairshop updatedShop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRepairShop'");
    }

    public boolean deleteRepairShop(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRepairShop'");
    }
}
