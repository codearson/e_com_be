package com.e_com.Dao;

import com.e_com.Domain.Bank;
import com.e_com.Dto.BankDto;


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
}
