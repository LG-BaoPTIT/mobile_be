package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Location;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.repositories.HotelRepository;
import com.sqa.project_sqa.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getDetailHotel(int hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    @Override
    public Hotel getHotelByRoom(Room room) {
        return hotelRepository.findHotelByRoomId(room.getId());
    }

    @Override
    public List<Hotel> getByLocations(Location location) {
        return hotelRepository.findByLocations(location);
    }
}
