package com.e_com.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Domain.User;
import com.e_com.Dto.UserDto;

/**
 * May 9, 2024 10:11:02 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Component
public class UserTransfomer implements BaseTransformer<User, UserDto> {

	@Autowired
	UserRoleTransfomer userRoleTransfomer;

	@Override
	public UserDto transform(User user) {
		UserDto userDto = null;
		if (user != null) {
			userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDto.setPassword(user.getPassword());
			userDto.setAddress(user.getAddress());
			userDto.setEmailAddress(user.getEmailAddress());
			userDto.setMobileNumber(user.getMobileNumber());
			userDto.setCreatedDate(user.getCreatedDate());
			userDto.setModifiedDate(user.getModifiedDate());
			userDto.setIsActive(user.getIsActive());
			if (user.getUserRole() != null) {
				userDto.setUserRoleDto(userRoleTransfomer.transform(user.getUserRole()));
			}
		}
		return userDto;
	}

	@Override
	public User reverseTransform(UserDto userDto) {
		User user = null;
		if (userDto != null) {
			user = new User();
			user.setId(userDto.getId());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setPassword(userDto.getPassword());
			user.setAddress(userDto.getAddress());
			user.setEmailAddress(userDto.getEmailAddress());
			user.setMobileNumber(userDto.getMobileNumber());
			user.setCreatedDate(userDto.getCreatedDate());
			user.setModifiedDate(userDto.getModifiedDate());
			user.setIsActive(userDto.getIsActive());
			if (userDto.getUserRoleDto() != null) {
				user.setUserRole(userRoleTransfomer.reverseTransform(userDto.getUserRoleDto()));
			}
		}
		return user;
	}

}
