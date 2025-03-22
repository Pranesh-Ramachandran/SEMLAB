package com.examly.springapp.service;

import com.examly.springapp.entity.ServiceENT;
import com.examly.springapp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Ensure this annotation is present
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceENT> getAllServices() {
        return serviceRepository.findAll();
    }

    public Page<ServiceENT> getAllServicesPaginated(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    public ServiceENT getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public ServiceENT createService(ServiceENT serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    public ServiceENT updateService(Long id, ServiceENT updatedServiceEntity) {
        Optional<ServiceENT> existingServiceOpt = serviceRepository.findById(id);
        if (existingServiceOpt.isPresent()) {
            ServiceENT existingService = existingServiceOpt.get();
            existingService.setName(updatedServiceEntity.getName());
            existingService.setDescription(updatedServiceEntity.getDescription());
            existingService.setPrice(updatedServiceEntity.getPrice());
            return serviceRepository.save(existingService);
        }
        return null; // Service not found
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
