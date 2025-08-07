package com.e_com.Transformer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Dao.ProductDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.Reviews;
import com.e_com.Domain.Product;
import com.e_com.Domain.User;
import com.e_com.Dto.ReviewsDto;
import com.e_com.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

/**
* Title: ReviewsTransformer.java. Company: www.codearson.com
* Author: Mohomed Wazeem
* Date: 07 August 2025
* Time: 5:04:23 PM
* Version: 1.0
**/

@Slf4j
@Component
public class ReviewsTransformer implements BaseTransformer<Reviews, ReviewsDto> {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public ReviewsDto transform(Reviews reviews) {
        if (reviews == null) {
            return null;
        }

        ReviewsDto reviewsDto = new ReviewsDto();
        reviewsDto.setId(reviews.getId());
        reviewsDto.setRating(reviews.getRating());
        reviewsDto.setReview(reviews.getReview());
        reviewsDto.setCreatedAt(reviews.getCreatedAt());
        reviewsDto.setUpdatedAt(reviews.getUpdatedAt());
        reviewsDto.setIsActive(reviews.getIsActive());

        if (reviews.getUser() != null) {
            reviewsDto.setUserId(reviews.getUser().getId());
        }

        if (reviews.getProduct() != null) {
            reviewsDto.setProductId(reviews.getProduct().getId());
            try {
                ProductDto productDto = productTransformer.transform(reviews.getProduct());
                reviewsDto.setProduct(productDto);
            } catch (Exception e) {
                log.error("Error transforming product with ID {}: {}", reviews.getProduct().getId(), e.getMessage());
            }
        }

        return reviewsDto;
    }

    public List<ReviewsDto> transform(List<Reviews> reviewsList) {
        if (reviewsList == null) {
            return null;
        }
        return reviewsList.stream().map(this::transform).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Reviews reverseTransform(ReviewsDto reviewsDto) {
        if (reviewsDto == null) {
            return null;
        }

        Reviews reviews = new Reviews();
        reviews.setId(reviewsDto.getId());
        reviews.setRating(reviewsDto.getRating());
        reviews.setReview(reviewsDto.getReview());
        reviews.setCreatedAt(reviewsDto.getCreatedAt() != null ? reviewsDto.getCreatedAt() : LocalDateTime.now());
        reviews.setUpdatedAt(LocalDateTime.now());
        reviews.setIsActive(reviewsDto.getIsActive() != null ? reviewsDto.getIsActive() : true);

        if (reviewsDto.getUserId() != null) {
            User user = userDao.findUserById(reviewsDto.getUserId());
            reviews.setUser(user);
        }

        if (reviewsDto.getProductId() != null) {
            Product product = productDao.findById(reviewsDto.getProductId());
            reviews.setProduct(product);
        }

        return reviews;
    }
}
