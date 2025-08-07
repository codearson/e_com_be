package com.e_com.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dao.ReviewsDao;
import com.e_com.Domain.Reviews;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ReviewsDto;
import com.e_com.Service.ReviewsService;
import com.e_com.Service.BL.ReviewsServiceBL; // Import the BL layer
import com.e_com.Service.Utils.ServiceUtil;
import com.e_com.Transformer.ReviewsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ReviewsServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date 07 August 2025
 * @time 10:00:00 AM
 * @version 1.0
 **/


@Slf4j
@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsServiceBL reviewsServiceBL; // Autowire the BL layer

    @Autowired
    private ServiceUtil serviceUtil;

    
    public ResponseDto addReview(ReviewsDto reviewsDto) {
        log.info("ReviewsServiceImpl.addReview() invoked.");
        ResponseDto responseDto = null;
        try {
            if (reviewsDto == null || reviewsDto.getUserId() == null || reviewsDto.getProductId() == null || reviewsDto.getRating() == null) {
                log.info("Invalid Review data provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_ADD_REVIEW);
            }
            ReviewsDto savedReviewsDto = reviewsServiceBL.saveReview(reviewsDto);
            if (savedReviewsDto != null) {
                log.info("Review Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedReviewsDto);
            } else {
                log.info("Unable to save Review details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_ADD_REVIEW);
            }
        } catch (Exception e) {
            log.error("Exception occurs while adding review.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_ADD_REVIEW);
        }
        return responseDto;
    }

    
    public ResponseDto updateReview(ReviewsDto reviewsDto) {
        log.info("ReviewsServiceImpl.updateReview() invoked.");
        ResponseDto responseDto = null;
        try {
            if (reviewsDto == null || reviewsDto.getId() == null) {
                log.info("Invalid Review data provided for update.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_REVIEW);
            }
            ReviewsDto updatedReviewsDto = reviewsServiceBL.updateReview(reviewsDto);
            if (updatedReviewsDto != null) {
                log.info("Review Details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedReviewsDto);
            } else {
                log.info("Unable to update Review details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_REVIEW);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating review.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_REVIEW);
        }
        return responseDto;
    }

    
    public ResponseDto deleteReview(Integer id) {
        log.info("ReviewsServiceImpl.deleteReview() invoked with id: {}", id);
        ResponseDto responseDto = null;
        try {
            if (id == null) {
                log.info("Invalid review id provided for deletion.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_REVIEW);
            }
            reviewsServiceBL.deleteReview(id);
            log.info("Review deleted successfully.");
            responseDto = serviceUtil.getServiceResponse("Review deleted successfully");
        } catch (Exception e) {
            log.error("Exception occurs while deleting review.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_DELETE_REVIEW);
        }
        return responseDto;
    }

    
    
    public ResponseDto getReviewsByProductId(Integer productId) {
        log.info("ReviewsServiceImpl.getReviewsByProductId() invoked with productId: {}", productId);
        ResponseDto responseDto = null;
        try {
            if (productId == null) {
                log.info("Invalid product id provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_REVIEWS_BY_PRODUCT_ID);
            }
            List<ReviewsDto> reviewsDtos = reviewsServiceBL.getReviewsByProductId(productId);
            if (reviewsDtos != null && !reviewsDtos.isEmpty()) {
                log.info("Retrieved reviews for product id: {}", productId);
                responseDto = serviceUtil.getServiceResponse(reviewsDtos);
            } else {
                log.info("No reviews found for product id: {}", productId);
                responseDto = serviceUtil.getServiceResponse("No reviews found");
            }
        } catch (Exception e) {
            log.error("Exception occurs while getting reviews by product id.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_REVIEWS_BY_PRODUCT_ID);
        }
        return responseDto;
    }

    
    
    public ResponseDto getReviewsByUserId(Integer userId) {
        log.info("ReviewsServiceImpl.getReviewsByUserId() invoked with userId: {}", userId);
        ResponseDto responseDto = null;
        try {
            if (userId == null) {
                log.info("Invalid user id provided.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_REVIEWS_BY_USER_ID);
            }
            List<ReviewsDto> reviewsDtos = reviewsServiceBL.getReviewsByUserId(userId);
            if (reviewsDtos != null && !reviewsDtos.isEmpty()) {
                log.info("Retrieved reviews for user id: {}", userId);
                responseDto = serviceUtil.getServiceResponse(reviewsDtos);
            } else {
                log.info("No reviews found for user id: {}", userId);
                responseDto = serviceUtil.getServiceResponse("No reviews found");
            }
        } catch (Exception e) {
            log.error("Exception occurs while getting reviews by user id.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_REVIEWS_BY_USER_ID);
        }
        return responseDto;
    }

    
    public ResponseDto getAverageRating(Integer productId) {
        log.info("ReviewsServiceImpl.getAverageRating() invoked with productId: {}", productId);
        ResponseDto responseDto = null;
        try {
            if (productId == null) {
                log.info("Invalid product id provided for average rating.");
                return serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_AVERAGE_RATING);
            }
            Double averageRating = reviewsServiceBL.getAverageRatingByProductId(productId);
            log.info("Average rating for product id {}: {}", productId, averageRating);
            responseDto = serviceUtil.getServiceResponse(averageRating);
        } catch (Exception e) {
            log.error("Exception occurs while getting average rating.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_AVERAGE_RATING);
        }
        return responseDto;
    }
}