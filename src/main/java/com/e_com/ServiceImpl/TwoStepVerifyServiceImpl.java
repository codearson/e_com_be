package com.e_com.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Primary;

import com.e_com.Constants.ApplicationMessageConstants; // Still used for constants
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.TwoStepOtpVerificationRequestDto;
import com.e_com.Dto.TwoStepVerificationDto;
import com.e_com.Service.TwoStepVerifyService;
import com.e_com.Service.BL.TwoStepVerifyServiceBL;
import com.e_com.Service.Utils.ServiceUtil; // Keep if ServiceUtil is used elsewhere or for logging

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TwoStepVerifyServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 11 Jun 2025
 * @time 12:25 AM
 * @version 1.0
 **/

@Slf4j
@Service
@Primary // Mark this as the primary bean for TwoStepVerifyService interface
public class TwoStepVerifyServiceImpl implements TwoStepVerifyService {

	@Autowired
    private ServiceUtil serviceUtil;

    @Autowired
    private TwoStepVerifyServiceBL twoStepVerifyServiceBL;
    
    @Override
    public ResponseDto twoFaRequest(TwoStepOtpVerificationRequestDto request) {
        log.info("TwoStepVerifyServiceImpl.twoFaRequest invoked for email: {}", request.getEmail());
        ResponseDto responseDto;
        
        try {
            String success = twoStepVerifyServiceBL.twoFaRequest(request);
            
            if (success=="2-Step Verification email sent successfully") {
                log.info("2-Step Verification email successfully.");
                responseDto = serviceUtil.getServiceResponse("2-Step Verification email sent successfully!");
            } else {
                log.warn("Failed to send 2-Step Verification email.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_TWO_STEP_SEND);
            }
        } catch (Exception e) {
            log.error("Exception in 2-Step Verification process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_TWO_STEP_SEND);
        }
        
        return responseDto;
    }
    
    @Override
    public ResponseDto twoFaVerification(TwoStepVerificationDto request) {
        log.info("TwoStepVerifyServiceImpl.twoFaVerification invoked.");
        ResponseDto responseDto;

        try {
            boolean success = twoStepVerifyServiceBL.twoFaVerification(request);
            
            if (success) {
                log.info("2-step verification successfull.");
                responseDto = serviceUtil.getServiceResponse("2-step verification successfull!");
            } else {
                log.warn("2-step verification failed.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_TWO_STEP_VERIFICATION);
            }
        } catch (Exception e) {
            log.error("Exception in 2-step verification process.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_TWO_STEP_VERIFICATION);
        }
        
        return responseDto;
    }
   

   
}
