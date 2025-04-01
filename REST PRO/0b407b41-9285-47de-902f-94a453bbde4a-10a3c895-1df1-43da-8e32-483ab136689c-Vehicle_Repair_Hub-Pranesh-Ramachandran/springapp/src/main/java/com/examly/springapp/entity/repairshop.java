package com.examly.springapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "repair_shops")
@Getter
@Setter
public class repairshop{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double rating;
    private String owner;

    @ElementCollection
    private List<String> services;

    @ElementCollection
    private List<String> maintenanceTips;

    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<review> reviews;

    @OneToMany(mappedBy = "repairShop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ServiceENT> availableServices;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public List<String> getMaintenanceTips() {
        return maintenanceTips;
    }

    public void setMaintenanceTips(List<String> maintenanceTips) {
        this.maintenanceTips = maintenanceTips;
    }

    public List<review> getReviews() {
        return reviews;
    }

    public void setReviews(List<review> reviews) {
        this.reviews = reviews;
    }
}
