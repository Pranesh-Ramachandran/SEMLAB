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

   
    @GetMapping("/{id}")
    public ResponseEntity<repairshop> getRepairShopById(@PathVariable Long id) {
        repairshop repairShop = repairShopService.getRepairShopById(id);
        return repairShop != null
                ? new ResponseEntity<>(repairShop, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping
    public ResponseEntity<List<repairshop>> getAllRepairShops() {
        List<repairshop> shopList = repairShopService.getAllRepairShops();
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<repairshop> updateRepairShop(@PathVariable Long id, @RequestBody repairshop updatedShop) {
        repairshop updated = repairShopService.updateRepairShop(id, updatedShop);
        return updated != null
                ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRepairShop(@PathVariable Long id) {
        boolean isDeleted = repairShopService.deleteRepairShop(id);
        return isDeleted
                ? new ResponseEntity<>("{\"message\": \"Repair Shop deleted successfully\"}", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
