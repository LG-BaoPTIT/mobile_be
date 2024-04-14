package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.repositories.RoomRepository;
import com.sqa.project_sqa.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Override
    public List<Room> getRoomByHotel(Hotel hotel) {
        return roomRepository.findByHotel(hotel);
    }

    @Override
    public Room getRoomById(int id) {
        return roomRepository.findById(id).orElse(null);
    }
}
