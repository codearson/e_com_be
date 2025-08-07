package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ReviewsDto;
import com.e_com.Service.ReviewsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ReviewsController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 5:25:30 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("reviews")
public class ReviewsController {

    @Autowired
    ReviewsService reviewsService;

    @PostMapping("/add")
    public ResponseDto addReview(@RequestBody ReviewsDto reviewsDto) {
        log.info("ReviewsController.addReview() invoked.");
        return reviewsService.addReview(reviewsDto);
    }

    @PutMapping("/update")
    public ResponseDto updateReview(@RequestBody ReviewsDto reviewsDto) {
        log.info("ReviewsController.updateReview() invoked.");
        return reviewsService.updateReview(reviewsDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto deleteReview(@RequestParam("id") Integer id) {
        log.info("ReviewsController.deleteReview() invoked with id: {}", id);
        return reviewsService.deleteReview(id);
    }

    @GetMapping("/getByProductId")
    public ResponseDto getReviewsByProductId(@RequestParam("productId") Integer productId) {
        log.info("ReviewsController.getReviewsByProductId() invoked with productId: {}", productId);
        return reviewsService.getReviewsByProductId(productId);
    }

    @GetMapping("/getByUserId")
    public ResponseDto getReviewsByUserId(@RequestParam("userId") Integer userId) {
        log.info("ReviewsController.getReviewsByUserId() invoked with userId: {}", userId);
        return reviewsService.getReviewsByUserId(userId);
    }

    @GetMapping("/getAverageRating")
    public ResponseDto getAverageRating(@RequestParam("productId") Integer productId) {
        log.info("ReviewsController.getAverageRating() invoked with productId: {}", productId);
        return reviewsService.getAverageRating(productId);
    }
}
