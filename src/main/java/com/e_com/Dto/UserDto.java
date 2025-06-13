package com.e_com.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * May 9, 2025 
 * 10:09:25 AM
 * @author Lathusan Thurairajah
 **/

@Data
public class UserDto {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String userUrl;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private String address;
	private String emailAddress;
	private String mobileNumber;
	private String whatsappNumber;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private Boolean isActive;
	private String about;
	private Boolean twoStepVerification;
	private UserRoleDto userRoleDto;

}
