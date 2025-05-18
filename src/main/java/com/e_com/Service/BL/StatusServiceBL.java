package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.StatusDao;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.StatusDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StatusServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 6:58:20 PM
 * @version 1.0
 **/

@Slf4j
@Service
public class StatusServiceBL {

    @Autowired
    private StatusDao statusDao;

    public StatusDto saveStatus(StatusDto statusDto) {
        log.info("StatusServiceBL.saveStatus() invoked.");
        return statusDao.saveStatus(statusDto);
    }
    
    public StatusDto updateStatus(StatusDto statusDto) {
        log.info("StatusServiceBL.updateStatus() invoked.");
        return statusDao.updateStatus(statusDto);
    }
    
    public StatusDto updateForStatus(Integer statusId, Boolean status) {
        log.info("StatusServiceBL.updateForStatus() invoked with statusId: {}, status: {}", statusId, status);
        StatusDto statusDto = statusDao.checkStatusAvailability(statusId);
        if (statusDto != null) {
            statusDto.setIsActive(status);
            return statusDao.updateStatus(statusDto);
        } else {
            return null;
        }
    }
    
    public PaginatedResponseDto getAllPageStatus(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("StatusServiceBL.getAllPageStatus() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return statusDao.getAllPageStatus(pageNumber, pageSize, status, searchParameters);
    }
    
    public List<StatusDto> getAllStatus(String statusName) {
        log.info("StatusServiceBL.getAllStatus() invoked with statusName: {}", statusName);
        return statusDao.getAllStatus(statusName);
    }



}
