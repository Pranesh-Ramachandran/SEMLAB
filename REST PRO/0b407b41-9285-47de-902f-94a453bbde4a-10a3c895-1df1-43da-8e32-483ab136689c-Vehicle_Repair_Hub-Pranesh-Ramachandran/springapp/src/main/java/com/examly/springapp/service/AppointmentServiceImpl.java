package com.examly.springapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import com.examly.springapp.repository.AppointmentRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.entity.Appointment;
import com.examly.springapp.entity.User;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));
    }

    @Override
    public Page<Appointment> getAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public Appointment updateAppointment(Integer id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));
    
        existingAppointment.setRepairShop(updatedAppointment.getRepairShop());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setServiceRequested(updatedAppointment.getServiceRequested());
        existingAppointment.setStatus(updatedAppointment.getStatus());
    
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public boolean deleteAppointment(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment with ID " + id + " not found");
        }
        appointmentRepository.deleteById(id);
        return true;
    }

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentsList() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));
    }

    @Override
    public Appointment createAppointment(Appointment appointment, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        
        appointment.setUser(user);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));
        return appointmentRepository.findByUser(user);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));
    
        existingAppointment.setRepairShop(updatedAppointment.getRepairShop());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setServiceRequested(updatedAppointment.getServiceRequested());
        existingAppointment.setStatus(updatedAppointment.getStatus());
    
        return appointmentRepository.save(existingAppointment);
    }

  
    @Override
    public boolean deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id.intValue())) {
            throw new EntityNotFoundException("Appointment with ID " + id + " not found");
        }
        appointmentRepository.deleteById(id.intValue());
        return true;
    }
}
