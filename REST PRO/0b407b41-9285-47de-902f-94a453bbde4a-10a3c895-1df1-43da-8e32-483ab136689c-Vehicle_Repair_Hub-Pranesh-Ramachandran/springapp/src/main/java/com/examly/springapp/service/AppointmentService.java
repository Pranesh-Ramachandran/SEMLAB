package com.examly.springapp.service;

import com.examly.springapp.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(Integer id);
    Appointment getAppointmentById(Long id);
    Page<Appointment> getAllAppointments(Pageable pageable);
    List<Appointment> getAllAppointments();
    List<Appointment> getAllAppointmentsList();
    List<Appointment> getAppointmentsByUserId(Long userId);
    Appointment updateAppointment(Integer id, Appointment updatedAppointment);
    Appointment updateAppointment(Long id, Appointment updatedAppointment);
    boolean deleteAppointment(Integer id);
    boolean deleteAppointment(Long id);
}
