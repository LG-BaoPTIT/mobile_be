package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByHotel(Hotel hotel);

}
