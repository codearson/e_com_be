package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.BankDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BankService;

import lombok.extern.slf4j.Slf4j;


/**
 * Title: BankController.java
 * Company: www.codearson.com
 * Copyright: Copyright (c) 2025.
 * 
 * @author Asjath Musharrif Abusalif
 * @date 13 May 2025
 * @time 19:40:00
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bank")
public class BankController {

    @Autowired
    BankService bankService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto saveBank(@RequestBody BankDto bankDto) {
        log.info("BankController.saveBank() invoked");
        return bankService.saveBank(bankDto);
    }
}


