package com.e_com.Service.BL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.e_com.Dao.PasswordResetTokenDao;
import com.e_com.Dao.UserDao;
import com.e_com.Domain.PasswordResetToken;
import com.e_com.Domain.EmailVerificationToken;
import com.e_com.Domain.User;
import com.e_com.Dto.PasswordResetRequestDto;
import com.e_com.Dto.ResetPasswordDto;
import com.e_com.Dto.UserDto;
import com.e_com.Dto.UserLogsDto;
import com.e_com.Service.EmailService;
import com.e_com.Service.UserLogsService;
import com.e_com.Transformer.PasswordResetTokenTransformer;
import com.e_com.Transformer.EmailVerificationTokenTransformer;


import lombok.extern.slf4j.Slf4j;

/**
 * Title: PasswordResetServiceBL.java. Company: www.codearson.com | Copyright:
 * Copyright (C) 2025.
 * 
 * @author Lathusan Thurairajah.
 * @date Feb 21, 2025.
 * @version 1.0
 **/

@Slf4j
@Component
public class PasswordResetServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordResetTokenDao tokenDao;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private UserLogsService userLogsService;

	public String forgotPassword(PasswordResetRequestDto request) {
		String message;
	    log.info("PasswordResetServiceBL.forgotPassword processing for email: {}", request.getEmail());

	    Optional<User> userOpt = Optional.ofNullable(userDao.findByByEmail(request.getEmail()));

	    if (userOpt.isEmpty()) {
	        log.warn("User not found for email: {}", request.getEmail());
	        message = "User not found";
	        return message;
	    }

	    User user = userOpt.get();

	    // Check if the user has 'USER' role
	    List<UserDto> userRoleList = userDao.getUserByRole("USER");
	    boolean isUserRole = userRoleList.stream().anyMatch(u -> u.getId().equals(user.getId()));

	    if (isUserRole) {
	    	log.warn("Access denied for password reset request. User role: USER");
	    	message = "Access Denied: You do not have permission to change the password. Please contact your manager for assistance";
	        return message;
	    }

	    PasswordResetToken token = PasswordResetTokenTransformer.toEntity(user);
	    tokenDao.save(token);

	    String emailBody = "Hi " + user.getFirstName() + ",\n\n" + 
	                       "Your password reset code is: " + token.getToken() + "\n\n" + 
	                       "This token will expire in 1 hour.\n\n" + 
	                       "Regards,\nDelta POS";

	    emailService.sendEmail(request.getEmail(), "Password Reset Request", emailBody);

	    log.info("Password reset email sent to: {}", request.getEmail());
	    message = "Password reset email sent successfully";
	    return message;
	}

	public boolean resetPassword(ResetPasswordDto request) {
		log.info("PasswordResetServiceBL.resetPassword processing.");

		PasswordResetToken token = tokenDao.findByToken(request.getToken());

		if (token == null || token.getExpiryTokenTime().isBefore(LocalDateTime.now())) {
			log.warn("Invalid or expired token.");
			return false;
		}

		User user = token.getUser();
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		userDao.update(user);

		log.info("Password reset successfully for user: {}", user.getEmailAddress());
		
//      Prepare UserLogsDto


        UserLogsDto userLogsDto = new UserLogsDto();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId()); // Set user ID in UserDto
        userLogsDto.setUserDto(userDto); // Set UserDto in UserLogsDto
        userLogsDto.setSignOff(false);
        userLogsDto.setLogIn(LocalDateTime.now());
        userLogsDto.setDescription("Password reset successfully");

        // Call UserLogsService directly
        userLogsService.save(userLogsDto);

        log.info("User log saved successfully for user ID: {}", user.getId());
        
		return true;
	}
	
	public String emailTokenSend(PasswordResetRequestDto request) {
	    log.info("Processing password reset token for email: {}", request.getEmail());


	    EmailVerificationToken token = EmailVerificationTokenTransformer.toEntity(request.getEmail());
	    tokenDao.save(token);

	    String emailBody = "Hi, Your email verification token is: " + token.getToken() + "\n\n" +
	                       "This token will expire in 1 hour.\n\n" +
	                       "Regards,\n E-comm";

	    emailService.sendEmail(request.getEmail(), "Email Verification", emailBody);

	    log.info("Email verification token sent to: {}", request.getEmail());
	    return "Email verification token sent successfully";
	}


}
