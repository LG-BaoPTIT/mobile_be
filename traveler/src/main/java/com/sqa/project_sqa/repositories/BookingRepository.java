package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Booking;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);
    List<Booking> findByRoom(Room room);
}
