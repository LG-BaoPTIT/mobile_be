package com.sqa.project_sqa.payload.dto;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Room;
import com.sqa.project_sqa.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private int id;
    private Date checkInDate;
    private Date checkOutDate;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String status;
    private Room room;
    private User user;
    private Hotel hotel;
}
