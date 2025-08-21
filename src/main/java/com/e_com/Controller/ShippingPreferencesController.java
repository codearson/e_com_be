package com.e_com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.e_com.Dto.ShippingPreferencesDto;
import com.e_com.Service.ConditionsService;
import com.e_com.Service.ShippingPreferencesService;
import com.e_com.Service.BL.ShippingPreferencesServiceBL;

import lombok.extern.slf4j.Slf4j;

import com.e_com.Dto.ResponseDto;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("shippingPreferences")
public class ShippingPreferencesController {

    @Autowired
    ShippingPreferencesService shippingPreferencesService;
    
    @Autowired
    ShippingPreferencesServiceBL shippingPreferencesServiceBL;

	 @PostMapping("/save")
	 @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	 public ResponseDto saveShippingPreferences(@RequestBody ShippingPreferencesDto shippingPreferencesDto) {
		 log.info("ShippingPreferencesController.saveShippingPreferences() invoked");
		 return shippingPreferencesService.saveShippingPreferences(shippingPreferencesDto);
	    }
	 
	 @PostMapping("/update")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateShippingPreferences(@RequestBody ShippingPreferencesDto shippingPreferencesDto) {
	        log.info("ShippingPreferencesController.updateShippingPreferences() invoked");
	        return shippingPreferencesService.updateShippingPreferences(shippingPreferencesDto);
	    }
	 
	 @PutMapping("/updateStatus")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto updateShippingPreferencesStatus(@RequestParam("shippingPreferencesId") Integer shippingPreferencesId, @RequestParam("status") Boolean status) {
	        log.info("ShippingPreferencesController.updateShippingPreferencesStatus() invoked with shippingPreferencesId: {}, status: {}", shippingPreferencesId, status);
	        return shippingPreferencesService.updateShippingPreferencesStatus(shippingPreferencesId, status);
	    }
	 
	 @GetMapping("/findByUserId")
	// @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseDto findByUserId(@RequestParam("userId") Integer shippingPreferencesId) {
	    log.info("ShippingPreferencesController.findByUserId() invoked with userId: {}", shippingPreferencesId);
	    
	    List<ShippingPreferencesDto> preferences = shippingPreferencesServiceBL.getShippingPreferencesByUserId(shippingPreferencesId);

	    ResponseDto response = new ResponseDto();
	    response.setStatus(true);
	    response.setResponseDto(preferences);

	    return response;
	}


}
