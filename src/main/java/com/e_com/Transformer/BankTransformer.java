package com.e_com.Transformer;

import org.springframework.stereotype.Component;

import com.e_com.Domain.Bank;
import com.e_com.Dto.BankDto;


/**
 * Title: BankTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Asjath Musharrif Abusalif
 * @date May 13, 2025
 * @time 11:10:15 PM
 * @version 1.0
 **/

@Component
public class BankTransformer implements BaseTransformer<Bank, BankDto> {

    @Override
    public BankDto transform(Bank bank) {
        BankDto bankDto = null;
        if (bank != null) {
            bankDto = new BankDto();
            bankDto.setId(bank.getId());
            bankDto.setName(bank.getName());
            bankDto.setIsActive(bank.getIsActive());
        }
        return bankDto;
    }

    @Override
    public Bank reverseTransform(BankDto bankDto) {
        Bank bank = null;
        if (bankDto != null) {
            bank = new Bank();
            bank.setId(bankDto.getId());
            bank.setName(bankDto.getName());
            bank.setIsActive(bankDto.getIsActive());
        }
        return bank;
    }
}
