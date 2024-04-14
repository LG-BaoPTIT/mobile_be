package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;

import java.util.List;

public interface RoomService {
    List<Room> getRoomByHotel(Hotel hotel);

    Room getRoomById(int id);
}
