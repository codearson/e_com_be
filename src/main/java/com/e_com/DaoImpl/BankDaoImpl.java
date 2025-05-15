package com.e_com.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.BankDao;
import com.e_com.Domain.Bank;
import com.e_com.Dto.BankDto;
import com.e_com.Transformer.BankTransformer;

import lombok.extern.slf4j.Slf4j;



/**
 * Title: BankDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 15, 2025
 * @time 7:52:15 PM
 * @version 1.0
 **/


@Slf4j
@Repository
public class BankDaoImpl extends BaseDaoImpl<Bank> implements BankDao {

    @Autowired
    private BankTransformer bankTransformer;

    @Override
    @Transactional
    public BankDto saveBank(BankDto bankDto) {
        log.info("BankDaoImpl.saveBank() invoked.");
        Bank bank = bankTransformer.reverseTransform(bankDto);
        Bank savedBank = saveOrUpdate(bank);
        return bankTransformer.transform(savedBank);
    }
}
