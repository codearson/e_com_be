package com.e_com.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.UserRoleDto;
import com.e_com.Service.UserRoleService;
import com.e_com.Service.BL.UserRoleServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 
 * 1:41:22 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	UserRoleServiceBL userRoleServiceBL;
	
	@Override
	public ResponseDto saveUserRole(UserRoleDto UserTypeDto) {
		log.info("UserRoleServiceImpl.saveUser invoked.");
		ResponseDto responseDto = null;
		try {
			UserRoleDto saveUserRoleDto = userRoleServiceBL.saveUserRole(UserTypeDto);
			if (saveUserRoleDto != null) {
				log.info("User Role Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveUserRoleDto);
			} else {
				log.info("Unable to save User Role details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_USER_ROLE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving User Role details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER_ROLE_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllUserRole() {
		log.info("UserRoleServiceImpl.getAllUserRole() invoked");
		ResponseDto responseDto = null;
		try {
			List<UserRoleDto> userRoleDto = userRoleServiceBL.getAllUserRole();
			if (userRoleDto != null) {
				log.info("Retrieve All UserRole Details.");
				responseDto = serviceUtil.getServiceResponse(userRoleDto);
			} else {
				log.info("Unable to retrieve All UserRole details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USERROLE_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All UserRole details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USERROLE_DETAILS);
		}
		return responseDto;
	}

}
