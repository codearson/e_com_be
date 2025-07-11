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

import com.e_com.Dto.ResponseDto;
import com.e_com.Dto.ShippingAddressDto;
import com.e_com.Service.ShippingAddressService;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShippingAddressController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 15 May 2025
 * @time 18:59:31
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("shippingAddress")
public class ShippingAddressController {
	
	@Autowired
	ShippingAddressService shippingAddressService;
	
	@PostMapping("/save")
    public ResponseDto saveShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressController.saveShippingAddress() invoked");
        return shippingAddressService.saveShippingAddress(shippingAddressDto);
    }
	
	@PutMapping("/update")
    public ResponseDto updateShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto) {
        log.info("ShippingAddressController.updateShippingAddress() invoked");
        return shippingAddressService.updateShippingAddress(shippingAddressDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateShippingAddressStatus(@RequestParam("shippingAddressId") Integer shippingAddressId, @RequestParam("status") Boolean status) {
        log.info("ShippingAddressController.updateShippingAddressStatus() invoked with shippingAddressId: {}, status: {}", shippingAddressId, status);
        return shippingAddressService.updateShippingAddressStatus(shippingAddressId, status);
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageShippingAddress(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
                                      @RequestParam("status") Boolean status, WebRequest webRequest) {
        log.info("ShippingAddressController.getAllPageShippingAddress() invoked with pageNumber: {}, pageSize: {}, status: {}", 
                 pageNumber, pageSize, status);
        return shippingAddressService.getAllPageShippingAddress(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
    }
    
    @GetMapping("/getAllBySearch")
    public ResponseDto getAllShippingAddress(@RequestParam(value = "address", required = false) String address) {
        log.info("ShippingAddressController.getAllShippingAddress() invoked with address: {}", address);
        return shippingAddressService.getAllShippingAddress(address);
    }

}
