package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Location;
import com.sqa.project_sqa.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotel();
    }

    @GetMapping("/{hotelId}")
    public Hotel getDetailHotel(@PathVariable int hotelId) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        return  hotelService.getDetailHotel(hotelId);
    }
    @GetMapping("/location")
    public List<Hotel> getHotelByLocation(@RequestParam int locationId) {
        Location location = new Location();
        location.setId(locationId);
        return hotelService.getByLocations(location);
    }
}
