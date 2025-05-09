package com.e_com.Dao;

import java.util.List;

import com.e_com.Domain.UserRole;
import com.e_com.Dto.UserRoleDto;

/**
 * Feb 5, 2024 
 * 1:40:04 PM
 * @author Lathusan Thurairajah
 **/

public interface UserRoleDao extends BaseDao<UserRole>{
	
	UserRoleDto saveUserRole(UserRoleDto userRoleDto);
	
	List<UserRoleDto> getAllUserRole();

}
