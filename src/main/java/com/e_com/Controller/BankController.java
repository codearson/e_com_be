package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.BankDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.BankService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BankController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
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
    

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateBank(@RequestBody BankDto bankDto) {
        log.info("BankController.updateBank() invoked");
        return bankService.updateBank(bankDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateBankStatus(@RequestParam("bankId")Integer bankId, @RequestParam("status") Boolean status) {
        log.info("BankController.updateBankStatus() invoked with bankId: {}, status: {}", bankId, status);
        return bankService.updateBankStatus(bankId, status);
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageBank(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
        log.info("BankController.getAllPageBank() invoked with pageNumber: {}, pageSize: {}, status: {}",
                 pageNumber, pageSize, status);
        return bankService.getAllPageBank(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    public ResponseDto getAllBank(@RequestParam(value = "bankName", required = false) String bankName) {
        log.info("BankController.getAllBank() invoked with bankName: {}", bankName);
        return bankService.getAllBank(bankName);
    }
    
}
