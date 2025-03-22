package com.examly.springapp.service;

import com.examly.springapp.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AppointmentService {
    Page<Appointment> getAllAppointments(Pageable pageable);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment, Long userId);
    List<Appointment> getAppointmentsByUserId(Long userId);
    Appointment updateAppointment(Long id, Appointment updatedAppointment);
    boolean deleteAppointment(Long id);
    Appointment getAppointmentById(Integer id);
    Appointment updateAppointment(Integer id, Appointment updatedAppointment);
    boolean deleteAppointment(Integer id);
}
