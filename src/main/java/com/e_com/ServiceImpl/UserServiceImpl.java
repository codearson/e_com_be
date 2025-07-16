package com.e_com.ServiceImpl;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.JwtResponseDto;
import com.e_com.Dto.LoginRequestDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ChangePasswordDto;
import com.e_com.Exception.EmailDuplicationException;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dto.UserDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.UserService;
import com.e_com.Service.BL.UserServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:08:10 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ServiceUtil serviceUtil;

	@Autowired
	private UserServiceBL userServiceBL;

	@Override
	public ResponseDto registerUser(UserDto userDto) {
		log.info("UserServiceImpl.saveUser invoked.");
		ResponseDto responseDto = null;
		try {
			UserDto saveUserDto = userServiceBL.saveUser(userDto);
			log.info("User Details saved.");
			responseDto = serviceUtil.getServiceResponse(saveUserDto);
		} catch (Exception e) {
			log.error("Exception occurs while saving User details with the message {}", e.getLocalizedMessage());
			if (e instanceof EmailDuplicationException) {
				responseDto = serviceUtil.getExceptionServiceResponse(e);
			} else {
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER_DETAILS);
			}

		}
		return responseDto;
	}

	@Override
	public ResponseDto login(LoginRequestDto loginDto) {
		log.info("UserServiceImpl.login().invoked");
		try {
			JwtResponseDto jwtResponseDto = userServiceBL.login(loginDto);
			return serviceUtil.getServiceResponse(jwtResponseDto);

		} catch (Exception ex) {
			log.error("Exception occurs while login  with the message : {}", ex.getLocalizedMessage());
			return serviceUtil.getExceptionServiceResponse(ex);

		}
	}

	@Override
	public ResponseDto getAll(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("UserServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = userServiceBL.getAll(pageNumber, pageSize, status, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All User Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All User details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All User details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USER_DETAILS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getUserByName(String firstName, String lastName) {
		ResponseDto responseDto = null;
		try {
			List<UserDto> userDtoList = userServiceBL.getUserByName(firstName, lastName);
			if (userDtoList != null && !userDtoList.isEmpty()) {
				log.info("user detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(userDtoList);
			} else {
				log.info("Unable to Retrive user detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_USER_BY_NAME);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the user detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_BY_NAME);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getUserById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<UserDto> userDtoList = userServiceBL.getUserById(id);
			if (userDtoList != null && !userDtoList.isEmpty()) {
				log.info("user detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(userDtoList);
			} else {
				log.info("Unable to Retrive user detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_USER_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the user detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_BY_ID);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getUserByRole(String userRole) {
		ResponseDto responseDto = null;
		try {
			List<UserDto> userDtoList = userServiceBL.getUserByRole(userRole);
			if (userDtoList != null && !userDtoList.isEmpty()) {
				log.info("user detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(userDtoList);
			} else {
				log.info("Unable to Retrive user detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_USER_BY_ROLE);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the user detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_BY_ROLE);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updateUserDetails(UserDto userDto) {
		log.info("UserServiceImpl.update(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			UserDto updateUserDto = userServiceBL.updateUserDetails(userDto);
			if (updateUserDto != null) {
				log.info("User Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateUserDto);
			} else {
				log.info("Unable to update User details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_RE_USER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating User details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_RE_USER_DETAILS);
		}
		return responseDto;
	}
	

	@Override
	public ResponseDto updateUserStatus(Integer userId, Boolean status) {
		log.info("InvoiceServiceImpl.updateUserStatus(UserDto userDto) invoked");
		ResponseDto responseDto = null;
		try {
			UserDto updatedUserStatusDto = userServiceBL.updateUserStatus(userId, status);
			if (updatedUserStatusDto != null) {
				log.info("User Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedUserStatusDto);
			} else {
				log.info("Unable to update User status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_USER_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating User status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_STATUS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updatePassword(Integer userId, String password, Integer changedByUserId) {
	    log.info("UserServiceImpl.updatePassword() invoked");
	    ResponseDto responseDto = null;
	    try {
	        UserDto updatedUserPasswordDto = userServiceBL.updatePassword(userId, password, changedByUserId);
	        if (updatedUserPasswordDto != null) {
	            log.info("User Password updated.");
	            responseDto = serviceUtil.getServiceResponse(updatedUserPasswordDto);
	        } else {
	            log.info("Unable to update User Password.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_USER_PASSWORD);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurs while updating User password.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_PASSWORD);
	    }
	    return responseDto;
	}
	
	@Override
	public ResponseDto changePassword(String username, ChangePasswordDto changePasswordDto) {
        try {
            int result = userServiceBL.changePassword(username, changePasswordDto);
            if (result == 1) {
                return serviceUtil.getServiceResponse("Password changed successfully.");
            } else if (result == -1) {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_CHANGE_PASSWORD);
            } else if (result == -2) {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_CHANGE_PASSWORD);
            } else if (result == -3) {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_CHANGE_SAME_PASSWORD);
            } else {
                return serviceUtil.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_CHANGE_PASSWORD);
            }
        } catch (Exception e) {
            log.error("Exception occurs while changing password.", e);
            return serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.EX_CHANGE_PASSWORD);
        }
    }
	
	@Transactional
	@Override
	public ResponseDto getUserByEmailAddress(String emailAddress) {
		ResponseDto responseDto = null;
		try {
			List<UserDto> userDtoList = userServiceBL.getUserByEmailAddress(emailAddress);
			if (userDtoList != null && !userDtoList.isEmpty()) {
				log.info("user detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(userDtoList);
			} else {
				log.info("Unable to Retrive user detail with email address");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_USER_BY_EMAIL_ADDRESS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the user detail with email address.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_BY_EMAIL_ADDRESS);
		}
		return responseDto;
	}

}
