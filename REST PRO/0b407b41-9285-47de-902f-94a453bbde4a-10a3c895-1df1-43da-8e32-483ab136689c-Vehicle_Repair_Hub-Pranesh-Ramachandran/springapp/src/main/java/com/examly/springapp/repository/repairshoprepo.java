package com.examly.springapp.repository;

import com.examly.springapp.entity.repairshop; // Corrected entity name
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface repairshoprepo extends JpaRepository<repairshop, Long> { // Fixed naming

   
    @Query("SELECT r FROM repairshop r WHERE r.name = ?1")

    List<repairshop> findByName(String name);

    Optional<repairshop> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
