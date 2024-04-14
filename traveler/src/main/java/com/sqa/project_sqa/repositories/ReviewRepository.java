package com.sqa.project_sqa.repositories;

import com.sqa.project_sqa.entities.Hotel;
import com.sqa.project_sqa.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllReviewByHotel(Hotel hotel);
}
