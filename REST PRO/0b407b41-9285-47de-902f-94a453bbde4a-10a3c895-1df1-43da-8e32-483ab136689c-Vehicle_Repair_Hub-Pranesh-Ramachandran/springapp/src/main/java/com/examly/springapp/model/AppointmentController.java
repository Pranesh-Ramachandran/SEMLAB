package com.examly.springapp.model;

import com.examly.springapp.entity.Appointment;
import com.examly.springapp.entity.User;
import com.examly.springapp.entity.repairshop;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.repairshoprepo;
import com.examly.springapp.service.AppointmentService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private repairshoprepo repairShopRepository;

   
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam Long userId,
                                                         @RequestParam Long repairShopId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        appointment.setUser(user);

       repairshop repairShop = repairShopRepository.findById(repairShopId)
                .orElseThrow(() -> new RuntimeException("RepairShop not found with ID: " + repairShopId));
        appointment.setRepairShop(repairShop);

        Appointment savedAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPaginatedAndSortedAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Appointment> appointmentPage = appointmentService.getAllAppointments(pageable);

        return ResponseEntity.ok(Map.of(
                "totalPages", appointmentPage.getTotalPages(),
                "totalElements", appointmentPage.getTotalElements(),
                "appointments", appointmentPage.getContent()
        ));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable int id, @RequestBody Appointment updatedAppointment) {
        try {
            Appointment appointment = appointmentService.updateAppointment(id, updatedAppointment);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        boolean deleted = appointmentService.deleteAppointment(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
