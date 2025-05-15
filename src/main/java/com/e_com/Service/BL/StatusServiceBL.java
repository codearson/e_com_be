package com.e_com.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_com.Dao.StatusDao;
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
}
