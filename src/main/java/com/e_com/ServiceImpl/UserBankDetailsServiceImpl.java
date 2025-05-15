package com.e_com.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.UserBankDetailsDto;
import com.e_com.Service.UserBankDetailsService;
import com.e_com.Service.BL.UserBankDetailsServiceBL;
import com.e_com.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: UserBankDetailsServiceImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Component
@Slf4j
public class UserBankDetailsServiceImpl implements UserBankDetailsService {

	@Autowired
    private UserBankDetailsServiceBL userBankDetailsServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;
    
    @Override
    public ResponseDto save(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsServiceImpl.save invoked");
        ResponseDto responseDto = null;
        try {
        	UserBankDetailsDto savedUserBankDetailsDto = userBankDetailsServiceBL.save(userBankDetailsDto);
            if (savedUserBankDetailsDto != null) {
                log.info("User bank Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedUserBankDetailsDto);
            } else {
                log.info("Unable to save user bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_USER_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving user bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER_BANK_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto update(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsServiceImpl.update invoked");
        ResponseDto responseDto = null;
        try {
        	UserBankDetailsDto updatedUserBankDetailsDto = userBankDetailsServiceBL.update(userBankDetailsDto);
            if (updatedUserBankDetailsDto != null) {
                log.info("User bank details updated.");
                responseDto = serviceUtil.getServiceResponse(updatedUserBankDetailsDto);
            } else {
                log.info("Unable to update user bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_USER_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating user bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_BANK_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto updateStatus(Integer userBankDetailsId, Boolean status) {
        log.info("UserBankDetailsServiceImpl.updateStatus invoked with userBankDetailsId: {}, status: {}", userBankDetailsId, status);
        ResponseDto responseDto = null;
        try {
        	UserBankDetailsDto updatedUserBankDetailsDto = userBankDetailsServiceBL.updateStatus(userBankDetailsId, status);
            if (updatedUserBankDetailsDto != null) {
                log.info("User bank details Status updated.");
                responseDto = serviceUtil.getServiceResponse(updatedUserBankDetailsDto);
            } else {
                log.info("Unable to update User bank details status.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_USER_BANK_STATUS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating User bank Details status.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_BANK_STATUS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("UserBankDetailsServiceImpl.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        ResponseDto responseDto = null;
        try {
            PaginatedResponseDto paginatedResponseDto = userBankDetailsServiceBL.getAllPage(pageNumber, pageSize, status, searchParameters);
            if (paginatedResponseDto != null) {
                log.info("Retrieved paginated User bank details.");
                responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
            } else {
                log.info("Unable to retrieve paginated User bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USER_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving paginated User bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USER_BANK_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto getAll(String accountHolderName, String accountNumber) {
        log.info("UserBankDetailsServiceImpl.getAll() invoked with partnerName/accountNumber: {}", accountHolderName , accountNumber);
        ResponseDto responseDto = null;
        try {
            List<UserBankDetailsDto> userBankDetailsList = userBankDetailsServiceBL.getAll(accountHolderName, accountNumber);
            if (userBankDetailsList != null && !userBankDetailsList.isEmpty()) {
                log.info("Retrieved all User bank details.");
                responseDto = serviceUtil.getServiceResponse(userBankDetailsList);
            } else {
                log.info("Unable to retrieve all User bank details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USER_BANK_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving all User bank details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USER_BANK_DETAILS);
        }
        return responseDto;
    }
	
}
