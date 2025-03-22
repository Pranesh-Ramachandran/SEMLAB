package com.examly.springapp.repository;

import com.examly.springapp.entity.review;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reviewrepo extends JpaRepository<review, Long> {
    List<review> findByRepairShopId(Long repairShopId); 
    //Page<review> findByRepairShopId(Long repairShopId, Pageable pageable);

}
