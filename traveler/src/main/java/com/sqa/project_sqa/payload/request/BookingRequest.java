package com.sqa.project_sqa.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Date checkInDate;
    private Date checkOutDate;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String status;
    private int userId;
    private int roomId;
}
