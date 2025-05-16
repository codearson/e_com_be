package com.e_com.Service;

import java.util.Map;

import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ResponseDto;

/**
 * Title: ConditionsService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 5:44:11 pm
 * @version 1.0
 **/
public interface ConditionsService {

	ResponseDto saveConditions(ConditionsDto conditionsDto);
	
	ResponseDto updateConditions(ConditionsDto conditionsDto);
	
	ResponseDto updateConditionsStatus(Integer conditionsId, Boolean status);
	
	public ResponseDto getAllConditions();
	
	ResponseDto getAllPageConditions(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParameters);
    
	
}
