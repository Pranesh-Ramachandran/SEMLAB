package com.examly.springapp.model;


import com.examly.springapp.entity.repairshop;
import com.examly.springapp.service.repairshopserv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repairshops")
public class RepairShopController {

    @Autowired
    private repairshopserv repairShopService;

        @PostMapping
    public ResponseEntity<repairshop> createRepairShop(@RequestBody repairshop repairShop) {
        repairshop savedShop = repairShopService.createRepairShop(repairShop);
        return new ResponseEntity<>(savedShop, HttpStatus.CREATED);
    }

    // Get a repair shop by ID
    @GetMapping("/{id}")
    public ResponseEntity<repairshop> getRepairShopById(@PathVariable int id) {
        repairshop repairShop = repairShopService.getRepairShopById(id);
        return repairShop != null ?
                new ResponseEntity<>(repairShop, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all repair shops
    @GetMapping
    public ResponseEntity<List<repairshop>> getAllRepairShops() {
        List<repairshop> shopList = repairShopService.getAllRepairShops();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    // Update an existing repair shop
    @PutMapping("/{id}")
    public ResponseEntity<repairshop> updateRepairShop(@PathVariable int id, @RequestBody repairshop updatedShop) {
        repairshop updated = repairShopService.updateRepairShop(id, updatedShop);
        return updated != null ?
                new ResponseEntity<>(updated, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a repair shop
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRepairShop(@PathVariable int id) {
        boolean isDeleted = repairShopService.deleteRepairShop(id);
        return isDeleted ?
                new ResponseEntity<>("{\"message\": \"Repair Shop deleted successfully\"}", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
