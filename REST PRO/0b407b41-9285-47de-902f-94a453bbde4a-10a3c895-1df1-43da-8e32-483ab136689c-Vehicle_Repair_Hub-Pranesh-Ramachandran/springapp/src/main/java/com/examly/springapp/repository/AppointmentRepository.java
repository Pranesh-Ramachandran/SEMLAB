package com.examly.springapp.repository;

import com.examly.springapp.entity.Appointment;
import com.examly.springapp.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

   
    List<Appointment> findAppointmentsByServiceRequested(String serviceRequested);

 
    List<Appointment> findAppointmentsByUserId(int userId);

    List<Appointment> findByUser(User user);
}
