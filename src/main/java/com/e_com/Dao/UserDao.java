package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.User;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserDto;

/**
 * Feb 5, 2024 
 * 11:20:57 AM
 * @author Lathusan Thurairajah
 **/

public interface UserDao extends BaseDao<User>{
	
	UserDto saveUser(UserDto userDto);

	User loadByUsername(String username);

	User findByByEmail(String email);

	PaginatedResponseDto getAll(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
	
	List<UserDto> getUserByName(String firstName, String lastName);
	
	List<UserDto> getUserById(Integer id);
	
	List<UserDto> getUserByRole(String userRole);
	
	UserDto update(UserDto userDto);
	
	UserDto checkUserAvailability(Integer userId);
	
	public UserDto updateStatus(UserDto userDto);
	
	List<UserDto> getUserByEmailAddress(String emailAddress);

}
