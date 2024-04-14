package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByLocations(Location location);

    @Query(value = "SELECT h.id, h.name FROM Room r JOIN Hotel h ON r.hotel_id = h.id WHERE r.id = :roomId", nativeQuery = true)
    Hotel findHotelByRoomId(int roomId);
}
