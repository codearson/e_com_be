package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.StatusDto;

/**
 * @author Mohomed Wazeem
 * @date May 14, 2025
 * @time 6:19:38 PM
 **/

public interface StatusService {
    
    ResponseDto saveStatus(StatusDto statusDto);
    
    ResponseDto updateStatus(StatusDto statusDto);
    
    ResponseDto updateForStatus(Integer statusId, Boolean status);


}
