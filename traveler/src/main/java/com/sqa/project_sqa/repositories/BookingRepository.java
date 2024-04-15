package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Booking;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);
    List<Booking> findByRoom(Room room);

    @Query(value = "SELECT * FROM booking WHERE room_id = :roomId AND status != 'Đã hủy' AND ((check_in_date <= :checkInDate AND check_out_date >= :checkInDate) OR (check_in_date <= :checkOutDate AND check_out_date >= :checkOutDate) OR (check_in_date >= :checkInDate AND check_out_date <= :checkOutDate))", nativeQuery = true)
    List<Booking> findToCheckCreateBooking(@Param("roomId") int roomId, @Param("checkInDate") Date checkInDate, @Param("checkOutDate") Date checkOutDate);

}
