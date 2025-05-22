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

import com.e_com.Dto.OrderTrackingDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.OrderTrackingService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * Title: OrderTrackingController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Rifas Mohamed
 * @date 19 May 2025
 * @time 5:18:39 pm
 * @version 1.0
 **/
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("orderTracking")
public class OrderTrackingController {
	
	@Autowired
	OrderTrackingService orderTrackingService;
	
	@PostMapping("/save")
	 @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	 public ResponseDto saveOrderTracking(@RequestBody OrderTrackingDto orderTrackingDto) {
		 log.info("OrderTrackingController.saveOrderTracking() invoked");
		 return orderTrackingService.saveOrderTracking(orderTrackingDto);
	    }
	 
	 @PostMapping("/update")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateOrderTracking(@RequestBody OrderTrackingDto orderTrackingDto) {
	        log.info("OrderTrackingController.updateOrderTracking() invoked");
	        return orderTrackingService.updateOrderTracking(orderTrackingDto);
	    }
	 
	 @PutMapping("/updateStatus")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateOrderTrackingStatus(@RequestParam("orderTrackingId") Integer orderTrackingId, @RequestParam("status") Boolean status) {
	        log.info("OrderTrackingController.updateOrderTrackingStatus() invoked with orderTrackingId: {}, status: {}", orderTrackingId, status);
	        return orderTrackingService.updateOrderTrackingStatus(orderTrackingId, status);
	    }
	 
	 @GetMapping("/getAllPage")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto getAllPageOrderTracking(@RequestParam("pageNumber") int pageNumber, 
	                                       @RequestParam("pageSize") int pageSize,
	                                       @RequestParam("status") Boolean status,
	                                       WebRequest webRequest) {
	        log.info("OrderTrackingController.getAllPageOrderTracking() invoked with pageNumber: {}, pageSize: {}, status: {}", 
	                 pageNumber, pageSize, status);
	        return orderTrackingService.getAllPageOrderTracking(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	    }
	 
	 @GetMapping("/getAll")
		@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
		public ResponseDto getAllOrderTracking() {
			log.info("OrderTrackingController.getAll() invoked.");
			return orderTrackingService.getAllOrderTracking();
		}
}
