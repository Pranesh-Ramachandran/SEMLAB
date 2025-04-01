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

   
    public repairshop createRepairShop(repairshop repairShop) {
        return repairShopRepo.save(repairShop);
    }

   
    public repairshop getRepairShopById(Long id) {
        Optional<repairshop> repairShop = repairShopRepo.findById(id);
        return repairShop.orElse(null);
    }

    public List<repairshop> getAllRepairShops() {
        return repairShopRepo.findAll();
    }

   
    public repairshop updateRepairShop(Long id, repairshop updatedShop) {
        Optional<repairshop> existingShop = repairShopRepo.findById(id);
        if (existingShop.isPresent()) {
            updatedShop.setId(id); 
            return repairShopRepo.save(updatedShop);
        }
        return null;
    }

    
    public boolean deleteRepairShop(Long id) {
        if (repairShopRepo.existsById(id)) {
            repairShopRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
