package com.e_com.Dao;

import java.util.Map;

import com.e_com.Domain.Status;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.PaginatedResponseDto;
import com.e_com.Dto.StatusDto;

/**
 * Title: StatusDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 7:02:27 PM
 * @version 1.0
 **/

public interface StatusDao extends BaseDao<Status> {

    StatusDto saveStatus(StatusDto statusDto);
    
    StatusDto updateStatus(StatusDto statusDto);
    
    StatusDto checkStatusAvailability(Integer statusId);
    
    PaginatedResponseDto getAllPageStatus(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);


}
