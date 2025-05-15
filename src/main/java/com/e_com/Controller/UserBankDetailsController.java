package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.UserBankDetailsDto;
import com.e_com.Service.UserBankDetailsService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: UserBankDetailsController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date May 15, 2025
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("userBankDetails")
public class UserBankDetailsController {

	@Autowired
	UserBankDetailsService userBankDetailsService;
	
	@PostMapping("/save")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsController.save() invoked");
        return userBankDetailsService.save(userBankDetailsDto);
    }
	
	@PostMapping("/update")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody UserBankDetailsDto userBankDetailsDto) {
        log.info("UserBankDetailsController.update() invoked");
        return userBankDetailsService.update(userBankDetailsDto);
    }
    
    @PutMapping("/updateStatus")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestParam("userBankDetailsId") Integer userBankDetailsId, @RequestParam("status") Boolean status) {
        log.info("UserBankDetailsController.updateStatus() invoked with userBankDetailsId: {}, status: {}", userBankDetailsId, status);
        return userBankDetailsService.updateStatus(userBankDetailsId, status);
    }
    
    @GetMapping("/getAllPage")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
        log.info("UserBankDetailsController.getAllPage() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return userBankDetailsService.getAllPage(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }
    
    @GetMapping("/getAllBySearch")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll(@RequestParam(value = "accountHolderName", required = false) String accountHolderName, @RequestParam(value = "accountNumber", required = false) String accountNumber) {
        log.info("UserBankDetailsController.getAll() invoked with partnerName: {}", accountHolderName);
        return userBankDetailsService.getAll(accountHolderName, accountNumber);
    }
	
}
