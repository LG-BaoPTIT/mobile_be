package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Location;
import com.sqa.project_sqa.entities.Room;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotel();

    Hotel getDetailHotel(int hotelId);

    Hotel getHotelByRoom(Room room);
    List<Hotel> getByLocations(Location location);
}
