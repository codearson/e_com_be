package com.e_com.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.ConditionsDao;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.UserRoleDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ConditionsServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 6:15:02 pm
 * @version 1.0
 **/

@Slf4j
@Service
public class ConditionsServiceBL {

    @Autowired
    private ConditionsDao conditionsDao;

    public ConditionsDto saveConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsServiceBL.saveConditions() invoked.");
        return conditionsDao.saveConditions(conditionsDto);
    }
    
    public ConditionsDto updateConditions(ConditionsDto conditionsDto) {
        log.info("ConditionsServiceBL.updateConditions() invoked.");
        return conditionsDao.updateConditions(conditionsDto);
    }
    
    public ConditionsDto updateConditionsStatus(Integer conditionsId, Boolean status) {
        log.info("ConditionsServiceBL.updateConditionsStatus() invoked with conditionsId: {}, status: {}", conditionsId, status);
        ConditionsDto conditionsDto = conditionsDao.checkConditionsAvailability(conditionsId);
        if (conditionsDto != null) {
            conditionsDto.setIsActive(status);
            return conditionsDao.updateConditions(conditionsDto);
        } else {
            return null;
        }
    }
    
    public List<ConditionsDto> getAllConditions() {
		log.info("ConditionsServiceBL.getAllCondition()invoked");
		return conditionsDao.getAllConditions();
	}
    
    public PaginatedResponseDto getAllPageConditions(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("ConditionsServiceBL.getAllPageConditions() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return conditionsDao.getAllPageConditions(pageNumber, pageSize, status, searchParameters);
    }
}
