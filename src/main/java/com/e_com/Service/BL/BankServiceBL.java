package com.e_com.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.BankDao;
import com.e_com.Dto.BankDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankServiceBl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:54:59 PM
 * @version 1.0
 **/



@Slf4j
@Service
public class BankServiceBL {

   @Autowired
   private BankDao bankDao;

   public BankDto saveBank(BankDto bankDto) {
       log.info("BankServiceBL.saveBank() invoked.");
       return bankDao.saveBank(bankDto);
   }
}