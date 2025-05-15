package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.UserBankDetailsDto;

/**
 * Title: UserBankDetailsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

public interface UserBankDetailsService {

	ResponseDto save(UserBankDetailsDto userBankDetailsDto);
    
    ResponseDto update(UserBankDetailsDto userBankDetailsDto);
    
    ResponseDto updateStatus(Integer userBankDetailsId, Boolean status);
    
    ResponseDto getAllPage(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);	
    
    ResponseDto getAll(String accountHolderName, String accountNumber);
	
}
