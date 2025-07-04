package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.BrandDto;
import com.e_com.Dto.ConditionsDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ConditionsService;
import com.e_com.Service.Utils.HttpReqRespUtils;

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
	 
	 @PutMapping("/updateStatus")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateConditionsStatus(@RequestParam("conditionsId") Integer conditionsId, @RequestParam("status") Boolean status) {
	        log.info("ConditionsController.updateConditionsStatus() invoked with conditionsId: {}, status: {}", conditionsId, status);
	        return conditionsService.updateConditionsStatus(conditionsId, status);
	    }
	 
	 @GetMapping("/getAll")
		@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
		public ResponseDto getAllConditions() {
			log.info("ConditionsController.getAll() invoked.");
			return conditionsService.getAllConditions();
		}
	 
	 @GetMapping("/getAllPage")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto getAllPageConditions(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
	                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
	        log.info("ConditionsController.getAllPageConditions() invoked with pageNumber: {}, pageSize: {}, status: {}", 
	                 pageNumber, pageSize, status);
	        return conditionsService.getAllPageConditions(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	    }
	    
}

