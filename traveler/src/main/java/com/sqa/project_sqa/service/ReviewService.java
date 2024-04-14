package com.sqa.project_sqa.service;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Review;
import com.sqa.project_sqa.payload.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    List<Review> getByHotel(Hotel hotel);

    void createReview(ReviewRequest reviewRequest);

    void deleteReview(Review review);

    void updateReview(Review review);
}
