package com.e_com.Service;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ReviewsDto;

/**
 * Title: ReviewsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 5:43:14 PM
 * @version 1.0
 **/

public interface ReviewsService {
    
    ResponseDto addReview(ReviewsDto reviewsDto);
        
    ResponseDto updateReview(ReviewsDto reviewsDto);
    
    ResponseDto deleteReview(Integer id);
    
    ResponseDto getReviewsByProductId(Integer productId);
    
    ResponseDto getReviewsByUserId(Integer userId);
    
    ResponseDto getAverageRating(Integer productId);
}
