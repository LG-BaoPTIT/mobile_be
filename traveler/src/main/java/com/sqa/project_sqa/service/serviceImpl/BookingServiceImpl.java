package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Booking;
import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.dto.BookingDTO;
import com.sqa.project_sqa.payload.request.BookingRequest;
import com.sqa.project_sqa.repositories.BookingRepository;
import com.sqa.project_sqa.repositories.HotelRepository;
import com.sqa.project_sqa.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookingDTO> getAllByUser(User user) {
        List<Booking> bookings = bookingRepository.findByUser(user);
        List<BookingDTO> bookingDTOS = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setId(booking.getId());
            bookingDTO.setCheckInDate(booking.getCheckInDate());
            bookingDTO.setCheckOutDate(booking.getCheckOutDate());
            bookingDTO.setCustomerName(booking.getCustomerName());
            bookingDTO.setPhoneNumber(booking.getPhoneNumber());
            bookingDTO.setEmail(booking.getEmail());
            bookingDTO.setStatus(booking.getStatus());
            bookingDTO.setRoom(booking.getRoom());
            bookingDTO.setUser(booking.getUser());

            Hotel hotel = hotelRepository.findHotelByRoomId(booking.getRoom().getId());
            bookingDTO.setHotel(hotel);

            bookingDTOS.add(bookingDTO);
        }
        return  bookingDTOS;
    }

    @Override
    public void createBooking(BookingRequest bookingRequest) {
        User user = new User();
        user.setId(bookingRequest.getUserId());

        Room room = new Room();
        room.setId(bookingRequest.getRoomId());

        Booking booking = new Booking();
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setPhoneNumber(bookingRequest.getPhoneNumber());
        booking.setEmail(bookingRequest.getEmail());
        booking.setStatus(bookingRequest.getStatus());
        booking.setUser(user);
        booking.setRoom(room);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllByRoom(Room room) {
        return bookingRepository.findByRoom(room);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByTimeAndRoom(BookingRequest bookingRequest) {
        int roomId = bookingRequest.getRoomId();
        Date checkInDate = bookingRequest.getCheckInDate();
        Date checkOutDate = bookingRequest.getCheckOutDate();
        return bookingRepository.findToCheckCreateBooking(roomId,checkInDate,checkOutDate);
    }
}
