package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.Location;
import com.sqa.project_sqa.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("/")
    public List<Location> getAllLocations() {
        return  locationService.getAllLocations();
    }
}
