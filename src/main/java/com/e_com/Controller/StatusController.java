package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.StatusDto;
import com.e_com.Service.StatusService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StatusController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 6:06:35 PM
 * @version 1.0
 **/

@RestController
@RequestMapping("/status")
@Slf4j
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto saveStatus(@RequestBody StatusDto statusDto) {
        log.info("StatusController.saveStatus() invoked");
        return statusService.saveStatus(statusDto);
    }
    
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestBody StatusDto statusDto) {
        log.info("StatusController.updateStatus() invoked");
        return statusService.updateStatus(statusDto);
    }
}

