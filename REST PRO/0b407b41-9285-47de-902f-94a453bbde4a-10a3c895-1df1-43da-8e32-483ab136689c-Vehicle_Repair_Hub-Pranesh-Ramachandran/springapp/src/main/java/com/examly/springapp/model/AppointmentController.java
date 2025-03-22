package com.examly.springapp.model;

import com.examly.springapp.entity.Appointment;
import com.examly.springapp.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Long someId = 1L; 
        Appointment savedAppointment = appointmentService.createAppointment(appointment, someId);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(appointmentService.getAppointmentById(id), HttpStatus.OK);
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

        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
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
            return new ResponseEntity<>(appointmentService.updateAppointment(id, updatedAppointment), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable int id) {
        return appointmentService.deleteAppointment(id) ?
               new ResponseEntity<>(HttpStatus.NO_CONTENT) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
