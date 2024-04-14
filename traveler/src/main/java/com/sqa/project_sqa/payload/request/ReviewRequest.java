package com.sqa.project_sqa.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private String content;
    private int rating;
    private Date time;
    private int userId;
    private int hotelId;
}
