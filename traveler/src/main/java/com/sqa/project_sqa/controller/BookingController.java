package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.Booking;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.dto.BookingDTO;
import com.sqa.project_sqa.payload.request.BookingRequest;
import com.sqa.project_sqa.service.BookingService;
import com.sqa.project_sqa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @GetMapping("/user/{userId}")
    public List<BookingDTO> getBookingByUser(@PathVariable int userId) {
        User user = new User();
        user.setId(userId);
        return bookingService.getAllByUser(user);
    }

    @GetMapping("/room/{roomId}")
    public List<Booking> getBookingByRoom(@PathVariable int roomId) {
        Room room = new Room();
        room.setId(roomId);
        System.out.println("Có yêu cầu lấy danh sách booking của phòng có id: " + roomId);
        return bookingService.getAllByRoom(room);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {

        try {
            List<Booking> bookingList = bookingService.getBookingByTimeAndRoom(bookingRequest);
            if (!bookingList.isEmpty()) {
                return ResponseUtil.getResponseEntity("05","Phòng không còn trống trong khoảng thời gian này", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            bookingService.createBooking(bookingRequest);
            return ResponseEntity.ok("Tạo mới booking thành công");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.getResponseEntity("05","Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable int bookingId, @RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        List<Booking> bookingList = bookingService.getBookingByTimeAndRoom(bookingRequest);
        int length = bookingList.size();
        for (Booking b:bookingList) {
            if (b.getId() == bookingId) {
                length--;
            }
        }
        if (length > 0) {
            return ResponseUtil.getResponseEntity("05","Phòng không còn trống trong khoảng thời gian này", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!booking.getStatus().equals("Chưa thanh toán")) {
            return ResponseEntity.badRequest().body("Không thể chỉnh sửa booking này");
        }
        booking.setCheckInDate(bookingRequest.getCheckInDate());
        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setPhoneNumber(bookingRequest.getPhoneNumber());
        booking.setEmail(bookingRequest.getEmail());
        bookingService.updateBooking(booking);

        return ResponseEntity.ok("Booking có ID " + bookingId + " đã được chỉnh sửa.");
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        if (booking.getStatus().equals("Đã thanh toán")) {
            return ResponseEntity.badRequest().body("Không thể hủy booking đã thanh toán");
        }
        booking.setStatus("Đã hủy");
        bookingService.updateBooking(booking);
        return ResponseEntity.ok("Booking có ID " + bookingId + " đã được hủy.");
    }

    @PutMapping("/pay/{bookingId}")
    public ResponseEntity<String> payBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        if (booking.getStatus().equals("Đã hủy")) {
            return ResponseEntity.badRequest().body("Không thể thanh toán booking đã hủy.");
        }
        booking.setStatus("Đã thanh toán");
        bookingService.updateBooking(booking);
        return ResponseEntity.ok("Booking có ID " + bookingId + " đã được thanh toán");
    }
}
