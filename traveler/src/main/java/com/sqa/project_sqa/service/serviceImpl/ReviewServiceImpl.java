package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Review;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.request.ReviewRequest;
import com.sqa.project_sqa.repositories.ReviewRepository;
import com.sqa.project_sqa.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public List<Review> getByHotel(Hotel hotel) {
        return reviewRepository.findAllReviewByHotel(hotel);
    }

    @Override
    public void createReview(ReviewRequest reviewRequest) {
        Hotel hotel = new Hotel();
        hotel.setId(reviewRequest.getHotelId());

        User user = new User();
        user.setId(reviewRequest.getUserId());

        Review review = new Review();
        review.setContent(reviewRequest.getContent());
        review.setTime(reviewRequest.getTime());
        review.setRating(reviewRequest.getRating());
        review.setUser(user);
        review.setHotel(hotel);
        reviewRepository.save(review);
    }


    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public void updateReview(Review review) {
        reviewRepository.save(review);
    }
}
