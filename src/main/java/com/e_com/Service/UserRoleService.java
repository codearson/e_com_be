package com.e_com.Service;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.UserRoleDto;

/**
 * Feb 5, 2024 
 * 1:40:45 PM
 * @author Lathusan Thurairajah
 **/

public interface UserRoleService {
	
	public ResponseDto saveUserRole(UserRoleDto userRoleDto);
	
	public ResponseDto getAllUserRole();

}
