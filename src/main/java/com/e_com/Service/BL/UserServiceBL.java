package com.e_com.Service.BL;

import com.e_com.Domain.CustomUserDetails;
import com.e_com.Domain.User;
import com.e_com.Dto.JwtResponseDto;
import com.e_com.Dto.LoginRequestDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Exception.EmailDuplicationException;
import com.e_com.Service.EmailService;
import com.e_com.Service.Utils.JwtUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_com.Dao.UserDao;
import com.e_com.Dto.UserDto;
import com.e_com.Dto.ChangePasswordDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:17:32 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserServiceBL {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private EmailService emailService;

	public UserDto saveUser(UserDto userDto) {
		log.info("UserServiceBL.saveUser() invoked.");
		User user = userDao.findByByEmail(userDto.getEmailAddress());
		if (user != null) {
			log.info("exception thrown because of email duplication");
			throw new EmailDuplicationException(
					"User with the email id " + userDto.getEmailAddress() + " already exists.");
		} else {
			String encodedPassword = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(encodedPassword);
			return userDao.saveUser(userDto);
		}

	}

	public User getUserByUserName(String username) {
		log.info("UserServiceBL.getUserByUserName() invoked");
		return userDao.loadByUsername(username);
	}

	public JwtResponseDto login(LoginRequestDto request) {
		log.info("UserServiceBL.login() invoked");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		if (authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			return JwtResponseDto.builder().accessToken(jwtUtil.generateToken(userDetails)).build();
		} else {
			throw new RuntimeException("Authentication failed.");
		}
	}

	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("UserServiceBL.getAll()invoked");
		return userDao.getAll(pageNumber, pageSize, status, searchParams);
	}
	
	public List<UserDto> getUserByName(String firstName, String lastName) {
		log.info("UserServiceBL.getUserByName()invoked");
		return userDao.getUserByName(firstName, lastName);
	}
	
	public List<UserDto> getUserById(Integer id) {
		log.info("UserServiceBL.getUserById()invoked");
		return userDao.getUserById(id);
	}
	
	public List<UserDto> getUserByRole(String userRole) {
		log.info("UserServiceBL.getUserByRole()invoked");
		return userDao.getUserByRole(userRole);
	}
	
	public UserDto updateUserDetails(UserDto userDto) {
	    log.info("UserServiceBL.updateUserDetails() invoked.");
//	    String encodedPassword = passwordEncoder.encode(userDto.getPassword());
//		userDto.setPassword(encodedPassword);
	    userDto.setModifiedDate(LocalDateTime.now()); 
	    userDto.setCreatedDate(userDto.getCreatedDate());
	    return userDao.update(userDto);
	}
	
	public UserDto updateUserStatus(Integer userId, Boolean status) {
		UserDto userDto = userDao.checkUserAvailability(userId);
		if (userDto != null) {
			userDto.setIsActive(status);
			return userDao.updateStatus(userDto);
		} else {
			return null;
		}
	}
	
	public UserDto updatePassword(Integer userId, String password, Integer changedByUserId) {
	    UserDto userDto = userDao.checkUserAvailability(userId);
	    if (userDto != null) {
	        UserDto adminUser = userDao.checkUserAvailability(changedByUserId);
	        if (adminUser != null) {
	            String encodedPassword = passwordEncoder.encode(password);
	            userDto.setPassword(encodedPassword);
	            userDto.setModifiedDate(LocalDateTime.now());
	            
	            UserDto updatedUser = userDao.update(userDto);
	            if (updatedUser != null) {
	                String emailText = String.format(
	                    "Your password has been changed by %s.\n" +
	                    "Your new password is: %s",
	                    adminUser.getFirstName(),
	                    password
	                );
	                emailService.sendEmail(
	                    userDto.getEmailAddress(),
	                    "Password Change Notification",
	                    emailText
	                );
	                return updatedUser;
	            }
	        }
	    }
	    return null;
	}

    public int changePassword(String username, ChangePasswordDto changePasswordDto) {
        User user = userDao.loadByUsername(username);
        if (user == null) {
            return -1; // user not found
        }
        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
            return -2; // current password incorrect
        }
        // Prevent setting the same password
        if (passwordEncoder.matches(changePasswordDto.getNewPassword(), user.getPassword())) {
            return -3; // new password same as current
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        user.setModifiedDate(LocalDateTime.now());
        userDao.update(user);
        return 1; // success
    }
	
	public List<UserDto> getUserByEmailAddress(String emailAddress) {
		log.info("UserServiceBL.getUserByEmailAddress()invoked");
		return userDao.getUserByEmailAddress(emailAddress);
	}

}
