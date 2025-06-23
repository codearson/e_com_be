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

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.StatusDto;
import com.e_com.Service.StatusService;
import com.e_com.Service.Utils.HttpReqRespUtils;

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
@CrossOrigin(origins = "*")
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
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateForStatus(@RequestParam("statusId") Integer statusId, @RequestParam("status") Boolean status) {
        log.info("StatusController.updateForStatus() invoked with statusId: {}, status: {}", statusId, status);
        return statusService.updateForStatus(statusId, status);
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageStatus(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
        log.info("StatusController.getAllPageStatus() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return statusService.getAllPageStatus(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }

    @GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllStatus(@RequestParam(value = "statusName", required = false) String statusName) {
        log.info("StatusController.getAllStatus() invoked with statusName: {}", statusName);
        return statusService.getAllStatus(statusName);
    }

    
}

