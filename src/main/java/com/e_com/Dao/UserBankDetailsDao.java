package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.UserBankDetails;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserBankDetailsDto;

/**
 * Title: UserBankDetailsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

public interface UserBankDetailsDao  extends BaseDao<UserBankDetails> {

	UserBankDetailsDto save(UserBankDetailsDto userBankDetailsDto);
    
	UserBankDetailsDto update(UserBankDetailsDto userBankDetailsDto);
    
	UserBankDetailsDto checkAvailability(Integer userBankDetailsId);
    
    PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<UserBankDetailsDto> getAll(String accountHolderName, String accountNumber);
	
}
