package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Review;
import com.sqa.project_sqa.payload.request.ReviewRequest;
import com.sqa.project_sqa.service.ReviewService;
import com.sqa.project_sqa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/hotel/{hotelId}")
    public List<Review> getReviewByHotel(@PathVariable int hotelId) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        return reviewService.getByHotel(hotel);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createReview(@RequestBody ReviewRequest reviewRequest) {
        try {
            reviewService.createReview(reviewRequest);
            return ResponseEntity.ok("Tạo mới review thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.getResponseEntity("05","Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
