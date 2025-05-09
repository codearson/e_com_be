package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.UserRole;
import com.e_com.Dto.UserRoleDto;

/**
 * May 9, 2024 10:18:48 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Component
public class UserRoleTransfomer implements BaseTransformer<UserRole, UserRoleDto> {

	@Override
	public UserRoleDto transform(UserRole userRole) {
		UserRoleDto userTypeDto = null;
		if (userRole != null) {
			userTypeDto = new UserRoleDto();
			userTypeDto.setId(userRole.getId());
			userTypeDto.setUserRole(userRole.getUserRole());
			userTypeDto.setIsActive(userRole.getIsActive());
		}
		return userTypeDto;
	}

	@Override
	public UserRole reverseTransform(UserRoleDto userRoleDto) {
		UserRole userType = null;
		if (userRoleDto != null) {
			userType = new UserRole();
			userType.setId(userRoleDto.getId());
			userType.setUserRole(userRoleDto.getUserRole());
			userType.setIsActive(userRoleDto.getIsActive());
		}
		return userType;
	}

}
