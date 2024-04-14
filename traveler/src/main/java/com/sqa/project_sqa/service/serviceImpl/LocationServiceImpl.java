package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Location;
import com.sqa.project_sqa.repositories.LocationRepository;
import com.sqa.project_sqa.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
