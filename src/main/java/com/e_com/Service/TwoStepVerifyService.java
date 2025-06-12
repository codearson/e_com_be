package com.e_com.Service;

import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.TwoStepOtpVerificationRequestDto;
import com.e_com.Dto.TwoStepVerificationDto;

/**
 * Title: TwoFaService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 9 Jun 2025
 * @time 9:47:23 pm
 * @version 1.0
 **/

public interface TwoStepVerifyService {
	
	ResponseDto twoFaRequest(TwoStepOtpVerificationRequestDto request);
	
	ResponseDto twoFaVerification(TwoStepVerificationDto request);
	
}
