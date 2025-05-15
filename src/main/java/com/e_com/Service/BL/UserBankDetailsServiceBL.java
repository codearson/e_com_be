package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.UserBankDetailsDao;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserBankDetailsDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: UserBankDetailsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class UserBankDetailsServiceBL {

	@Autowired
    private UserBankDetailsDao userBankDetailsDao;
	
	public UserBankDetailsDto save(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsServiceBL.save() invoked.");
        return userBankDetailsDao.save(userBankDetailsDto);
    }
    
    public UserBankDetailsDto update(UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsServiceBL.update() invoked.");
        return userBankDetailsDao.update(userBankDetailsDto);
    }
    
    public UserBankDetailsDto updateStatus(Integer userBankDetailsId, Boolean status) {
        log.info("UserBankDetailsServiceBL.updateStatus() invoked with userBankDetailsId: {}, status: {}", userBankDetailsId, status);
        UserBankDetailsDto userBankDetailsDto = userBankDetailsDao.checkAvailability(userBankDetailsId);
        if (userBankDetailsDto != null) {
        	userBankDetailsDto.setIsActive(status);
            return userBankDetailsDao.update(userBankDetailsDto);
        } else {
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("UserBankDetailsServiceBL.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return userBankDetailsDao.getAllPage(pageNumber, pageSize, status, searchParameters);
    }
    
    public List<UserBankDetailsDto> getAll(String accountHolderName, String accountNumber) {
        log.info("UserBankDetailsServiceBL.getAll() invoked with brandName/accountNumber: {}", accountHolderName, accountNumber);
        return userBankDetailsDao.getAll(accountHolderName, accountNumber);
    }
	
}
