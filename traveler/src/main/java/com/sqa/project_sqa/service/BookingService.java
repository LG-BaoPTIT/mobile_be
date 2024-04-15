package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.Booking;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.dto.BookingDTO;
import com.sqa.project_sqa.payload.request.BookingRequest;

import java.sql.Date;
import java.util.List;

public interface BookingService {
    Booking getBookingById(int id);
    List<BookingDTO> getAllByUser(User user);
    void createBooking(BookingRequest bookingRequest);
    List<Booking> getAllByRoom(Room room);
    void updateBooking(Booking booking);

    List<Booking> getBookingByTimeAndRoom(BookingRequest bookingRequest);
}
