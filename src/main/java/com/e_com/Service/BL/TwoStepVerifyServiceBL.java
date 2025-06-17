package com.e_com.Service.BL;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Dao.TwoStepVerifyDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.TwoStepVerify;
import com.e_com.Domain.User;
import com.e_com.Dto.TwoStepOtpVerificationRequestDto;
import com.e_com.Dto.TwoStepVerificationDto;
import com.e_com.Service.EmailService;

import com.e_com.Transformer.TwoStepVerifyTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TwoStepVerifyServiceBL.java. Company: www.codearson.com Copyright:
 * Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 11 Jun 2025
 * @time 12:08 AM
 * @version 1.0
 **/

@Slf4j
@Component
public class TwoStepVerifyServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private TwoStepVerifyDao tokenDao;

	@Autowired
	private EmailService emailService;

	public String twoFaRequest(TwoStepOtpVerificationRequestDto request) {
		String message;
		log.info("TwoStepVerifyServiceBL.twoFaRequest processing for email: {}", request.getEmail());

		Optional<User> userOpt = Optional.ofNullable(userDao.findByByEmail(request.getEmail()));

		if (userOpt.isEmpty()) {
			log.warn("User not found for email: {}", request.getEmail());
			message = "User not found";
			return message;
		}

		User user = userOpt.get();

//		 Check if the user has 'USER' role
//		List<UserDto> userRoleList = userDao.getUserByRole("USER");
//		boolean isUserRole = userRoleList.stream().anyMatch(u -> u.getId().equals(user.getId()));
//
//		if (isUserRole) {
//			log.warn("Access denied for 2-Step verification request. User role: USER");
//			message = "Access Denied: You do not have permission to 2-Step verification. Please contact your manager for assistance";
//			return message;
//		}

		// 🧹 Step 1: Delete old token
		tokenDao.delete(user.getId());

		// ➕ Step 2: Create and save new token
		TwoStepVerify token = TwoStepVerifyTransformer.toEntity(user);
		tokenDao.save(token);

		String emailBody = "Hi " + user.getFirstName() + ",\n\n" + "Your 2-Step Verification code is: "
				+ token.getToken() + "\n\n" + "This token will expire in 1 hour.\n\n" + "Regards,\nDelta POS";

		emailService.sendEmail(request.getEmail(), "2-Step Verification Request", emailBody);

		log.info("2-Step Verification email sent to: {}", request.getEmail());
		message = "2-Step Verification email sent successfully";
		return message;
	}

	public boolean twoFaVerification(TwoStepVerificationDto request) {
		log.info("TwoStepVerifyServiceBL.twoFaVerification processing.");

		TwoStepVerify token = tokenDao.findByToken(request.getToken());
		log.info("Token received: {}", request.getToken());

		if (token == null || token.getExpiryTokenTime().isBefore(LocalDateTime.now())) {
			log.warn("Invalid or expired token.");
			return false;
		}

		// ✅ Get user and update twoStepVerification flag
		User user = token.getUser(); // assuming TwoStepVerify has a User reference
		if (user != null) {
			user.setTwoStepVerification(true);  
			userDao.update(user); 
			log.info("User twoStepVerification flag set to true for userId: {}", user.getId());
		}

		return true;
	}
}
