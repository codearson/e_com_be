package com.e_com.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.StatusDao;
import com.e_com.Domain.Brand;
import com.e_com.Domain.Status;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.StatusDto;
import com.e_com.Transformer.StatusTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StatusDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 9:01:44 PM
 * @version 1.0
 **/

@Slf4j
@Repository
public class StatusDaoImpl extends BaseDaoImpl<Status> implements StatusDao{

    @Autowired
    private StatusTransformer statusTransformer;

    @Transactional
    public StatusDto saveStatus(StatusDto statusDto) {
        log.info("StatusDaoImpl.saveStatus() invoked.");
        Status status = statusTransformer.reverseTransform(statusDto);
        Status savedStatus = saveOrUpdate(status);
        return statusTransformer.transform(savedStatus);
    }
}
