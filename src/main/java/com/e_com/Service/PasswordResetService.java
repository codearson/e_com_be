package com.e_com.Service;

import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResetPasswordDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: PasswordResetService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

public interface PasswordResetService {
	
	ResponseDto forgotPassword(PasswordResetRequestDto request);
	
	ResponseDto resetPassword(ResetPasswordDto request);
	
	ResponseDto emailTokenSend(PasswordResetRequestDto request);
	
}
