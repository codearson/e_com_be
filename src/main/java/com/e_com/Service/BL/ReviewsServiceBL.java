package com.e_com.Service.BL;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ReviewsDao;
import com.e_com.Domain.Reviews;
import com.e_com.Dto.ReviewsDto;
import com.e_com.Transformer.ReviewsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ReviewsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 5:56:05 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class ReviewsServiceBL {

    @Autowired
    private ReviewsDao reviewsDao;

    @Autowired
    private ReviewsTransformer reviewsTransformer;

    public ReviewsDto saveReview(ReviewsDto reviewsDto) {
        log.info("ReviewsServiceBL.saveReview() invoked.");
        if (reviewsDto != null) {
            reviewsDto.setCreatedAt(LocalDateTime.now());
            reviewsDto.setUpdatedAt(reviewsDto.getCreatedAt());
        }
        Reviews reviews = reviewsTransformer.reverseTransform(reviewsDto);
        Reviews savedReviews = reviewsDao.saveOrUpdate(reviews);
        return reviewsTransformer.transform(savedReviews);
    }

    public ReviewsDto updateReview(ReviewsDto reviewsDto) {
        log.info("ReviewsServiceBL.updateReview() invoked.");
        Reviews existingReview = reviewsDao.findById(reviewsDto.getId());
        if (existingReview == null) {
            log.info("Review with id {} not found.", reviewsDto.getId());
            return null;
        }
        if (reviewsDto != null) {
            reviewsDto.setCreatedAt(existingReview.getCreatedAt());
            reviewsDto.setUpdatedAt(LocalDateTime.now());
        }
        Reviews reviews = reviewsTransformer.reverseTransform(reviewsDto);
        Reviews updatedReviews = reviewsDao.saveOrUpdate(reviews);
        return reviewsTransformer.transform(updatedReviews);
    }

    public void deleteReview(Integer id) {
        log.info("ReviewsServiceBL.deleteReview() invoked with id: {}", id);
        reviewsDao.delete(id);
    }

    public List<ReviewsDto> getReviewsByProductId(Integer productId) {
        log.info("ReviewsServiceBL.getReviewsByProductId() invoked with productId: {}", productId);
        List<Reviews> reviews = reviewsDao.findByProductId(productId);
        return reviewsTransformer.transform(reviews);
    }

    public List<ReviewsDto> getReviewsByUserId(Integer userId) {
        log.info("ReviewsServiceBL.getReviewsByUserId() invoked with userId: {}", userId);
        List<Reviews> reviews = reviewsDao.findByUserId(userId);
        return reviewsTransformer.transform(reviews);
    }

    public Double getAverageRatingByProductId(Integer productId) {
        log.info("ReviewsServiceBL.getAverageRatingByProductId() invoked with productId: {}", productId);
        return reviewsDao.getAverageRatingByProductId(productId);
    }
}
