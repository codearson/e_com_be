package com.e_com.Dao;

import java.util.List;
import java.util.Map;

import com.e_com.Domain.Bank;
import com.e_com.Dto.BankDto;
import com.e_com.Dto.PaginatedResponseDto;

/**
 * Title: BankDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:50:51 PM
 * @version 1.0
 **/


public interface BankDao extends BaseDao<Bank> {

    BankDto saveBank(BankDto bankDto);
    
    BankDto updateBank(BankDto bankDto);
    
    BankDto checkBankAvailability(Integer bankId);
    
    PaginatedResponseDto getAllPageBank(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
    List<BankDto> getAllBank(String bankName);
}
