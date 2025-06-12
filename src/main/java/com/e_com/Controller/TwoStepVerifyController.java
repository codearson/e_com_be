package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResetPasswordDto;
import com.e_com.Dto.ResponseDto; // Assuming ResponseDto exists and is used
import com.e_com.Dto.TwoStepOtpVerificationRequestDto; // DTO for initiating/verifying OTP
import com.e_com.Dto.TwoStepVerificationDto;
import com.e_com.Service.PasswordResetService;
import com.e_com.Service.TwoStepVerifyService; // Your 2-step verification service interface
import com.e_com.Service.BL.PasswordResetServiceBL;
import com.e_com.Service.BL.TwoStepVerifyServiceBL;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TwoStepVerifyController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 11 Jun 2025
 * @time 12:35 AM
 * @version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/auth/two-step-verify") // A distinct path for 2-step verification
@CrossOrigin(origins = "*") // Allows requests from any origin (consider restricting in production)
public class TwoStepVerifyController {

	 @Autowired
	    private TwoStepVerifyService twoStepVerifyService;
	    @Autowired
	    private TwoStepVerifyServiceBL twoStepVerifyServiceBL;

	    @PostMapping("/twoFaRequest")
	    public String twoFaRequest(@RequestBody TwoStepOtpVerificationRequestDto request) {
	    	String message = twoStepVerifyServiceBL.twoFaRequest(request);
	        return message;
	    }

	    @PostMapping("/twoFaVerification")
	    public String twoFaVerification(@RequestBody TwoStepVerificationDto request) {
	    	twoStepVerifyService.twoFaVerification(request);
	        return "2-Step Verification is successfull!";
	    }

}
