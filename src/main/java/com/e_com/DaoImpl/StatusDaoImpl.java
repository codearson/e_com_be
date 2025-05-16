package com.e_com.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e_com.Dao.StatusDao;
import com.e_com.Domain.Brand;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Service.Utils.HttpReqRespUtils;
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
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public StatusDto saveStatus(StatusDto statusDto) {
        log.info("StatusDaoImpl.saveStatus() invoked.");
        Status status = statusTransformer.reverseTransform(statusDto);
        Status savedStatus = saveOrUpdate(status);
        return statusTransformer.transform(savedStatus);
    }
   
    @Transactional
    public StatusDto updateStatus(StatusDto statusDto) {
        log.info("StatusDaoImpl.updateStatus() invoked.");
        Status status = statusTransformer.reverseTransform(statusDto);
        Status updatedStatus = saveOrUpdate(status);
        return statusTransformer.transform(updatedStatus);
    }
    
    @Transactional
    public StatusDto checkStatusAvailability(Integer statusId) {
        log.info("StatusDaoImpl.checkStatusAvailability() invoked with statusId: {}", statusId);
        
        Criteria criteria = getCurrentSession().createCriteria(Status.class, "status");
        criteria.add(Restrictions.eq("status.id", statusId)); // this line kept the same, assuming "status" alias matches your entity
        
        Status status = (Status) criteria.uniqueResult(); // fetch the result from DB
        StatusDto statusDto = null;
        if (status != null) {
            statusDto = statusTransformer.transform(status); // transform into DTO
        }
        return statusDto;
    }
    
    @Override
    @Transactional
    public PaginatedResponseDto getAllPageStatus(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters) {
        log.info("StatusDaoImpl.getAllPageStatus() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        PaginatedResponseDto paginatedResponseDto = null;
        List<Status> statusList = null;
        int recordCount = 0;

        // Modify the count query to consider the status filter
        String countString = "SELECT COUNT(*) FROM status";
        if (status != null) {
            countString += " WHERE is_active = " + (status ? "true" : "false");
        }
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(Status.class, "status");

        // Add status filter if provided
        if (status != null) {
            criteria.add(Restrictions.eq("isActive", status));
        }

        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        statusList = criteria.list();

        if (statusList != null && !statusList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(statusList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(statusList.stream().map(statusObj -> {
                return statusTransformer.transform(statusObj);
            }).collect(Collectors.toList()));
        }

        return paginatedResponseDto;
    }
    
    @Override
    @Transactional
    public List<StatusDto> getAllStatus(String statusName) {
        log.info("StatusDaoImpl.getAllStatus() invoked with statusName: {}", statusName);
        Criteria criteria = getCurrentSession().createCriteria(Status.class, "status");

        // Add statusName filter if provided
        if (statusName != null && !statusName.isEmpty()) {
            criteria.add(Restrictions.ilike("statusName", "%" + statusName + "%"));
        }

        List<Status> statusList = criteria.list();
        return statusList.stream()
                         .map(status -> statusTransformer.transform(status))
                         .collect(Collectors.toList());
    }




}
