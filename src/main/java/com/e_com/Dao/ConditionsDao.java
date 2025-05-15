package com.e_com.Dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.e_com.Domain.Conditions;
import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.UserRoleDto;

/**
 * Title: ConditionsDao.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 6:19:50 pm
 * @version 1.0
 **/
public interface ConditionsDao extends BaseDao<Conditions> {
	
	ConditionsDto saveConditions(ConditionsDto conditionsDto);
	
	ConditionsDto updateConditions(ConditionsDto conditionsDto);
	
	ConditionsDto checkConditionsAvailability(Integer conditionsId);
	
	List<ConditionsDto> getAllConditions();

}
