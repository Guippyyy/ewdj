package com.project.ewdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Location;
import com.project.ewdj.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository lRepository;

    public void save(Location l) {
        lRepository.save(l);
    }
}
