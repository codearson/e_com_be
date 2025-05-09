package com.e_com.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.UserRoleDao;
import com.e_com.Dto.UserRoleDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:41:08 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserRoleServiceBL {
	
	@Autowired
	UserRoleDao userRoleDao;
	
	public UserRoleDto saveUserRole(UserRoleDto userRoleDto) {
		log.info("UserRoleServiceBL.saveUserRole() invoked.");
		return userRoleDao.saveUserRole(userRoleDto);
	}
	
	public List<UserRoleDto> getAllUserRole() {
		log.info("UserRoleServiceBL.getAllUser()invoked");
		return userRoleDao.getAllUserRole();
	}


}
