package com.examly.springapp.model;

import com.examly.springapp.entity.ServiceENT;
import com.examly.springapp.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceENT> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceENT getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public ServiceENT createService(@RequestBody ServiceENT service) {
        return serviceService.createService(service);
    }

    @PutMapping("/{id}")
    public ServiceENT updateService(@PathVariable Long id, @RequestBody ServiceENT service) {
        return serviceService.updateService(id, service);
    }

    @DeleteMapping("/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "Service deleted successfully!";
    }
}
