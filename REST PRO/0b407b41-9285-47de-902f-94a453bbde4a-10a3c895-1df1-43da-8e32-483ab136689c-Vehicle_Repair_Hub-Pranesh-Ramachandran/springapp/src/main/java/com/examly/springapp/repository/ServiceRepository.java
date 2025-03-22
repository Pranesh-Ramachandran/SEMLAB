package com.examly.springapp.repository;

import com.examly.springapp.entity.ServiceENT;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceENT, Long> {
    Page<ServiceENT> findAll(Pageable pageable);

    Optional<ServiceENT> findByName(String name);
    //  //JPQL query to find services by category
    // @Query("SELECT s FROM ServiceENT s WHERE s.category = :category")
    // List<ServiceENT> findByCategory(@Param("category") String category);
}
