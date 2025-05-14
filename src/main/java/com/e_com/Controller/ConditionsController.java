package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ConditionsService;

import lombok.extern.slf4j.Slf4j;


/**
 * Title: ConditionsController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 14 May 2025
 * @time 5:29:09 pm
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("conditions")
public class ConditionsController {
	
	 @Autowired
	 ConditionsService conditionsService;

	 @PostMapping("/save")
	 @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	 public ResponseDto saveConditions(@RequestBody ConditionsDto conditionsDto) {
		 log.info("ConditionsController.saveConditions() invoked");
		 return conditionsService.saveConditions(conditionsDto);
	    }
	 
	 @PostMapping("/update")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateConditions(@RequestBody ConditionsDto conditionsDto) {
	        log.info("ConditionsController.updateConditions() invoked");
	        return conditionsService.updateConditions(conditionsDto);
	    }
	    
}

