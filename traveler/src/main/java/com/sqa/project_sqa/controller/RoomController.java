package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @GetMapping("/{roomId}")
    public Room getRoomById (@PathVariable int roomId) {
        return  roomService.getRoomById(roomId);
    }
    @GetMapping("/hotel/{hotelId}")
    public List<Room> getByHotels(@PathVariable int hotelId) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        return  roomService.getRoomByHotel(hotel);
    }
}
