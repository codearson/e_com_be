package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.BankDto;
import com.e_com.Dto.ResponseDto;



/**
 * Title: BankService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:53:53 PM
 * @version 1.0
 **/




public interface BankService {

    ResponseDto saveBank(BankDto bankDto);
    
    ResponseDto updateBank(BankDto bankDto); 
    
    ResponseDto updateBankStatus(Integer bankId, Boolean status);
    
    ResponseDto getAllPageBank(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    ResponseDto getAllBank(String bankName);
}
