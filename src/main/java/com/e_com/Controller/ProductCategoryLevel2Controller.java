package com.e_com.Controller;

import java.util.Map;

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

import com.e_com.Dto.ProductCategoryLevel2Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel2Service;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductSubCategoryController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Muhila Vijayakumar
 * @date May 15, 2025
 * @time 3:10:30 PM
 * @version 1.0
 **/
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ProductCategoryLevel2")
public class ProductCategoryLevel2Controller {

	@Autowired
	ProductCategoryLevel2Service productCategoryLevel2Service;
	
	@GetMapping("/getAllPage")
	public ResponseDto getAllPageProductCategoryLevel2(@RequestParam("pageNumber") int pageNumber, 
												 @RequestParam("pageSize") int pageSize,
												 @RequestParam("status") Boolean status, 
												 WebRequest webRequest) {
		log.info("ProductCategoryLevel2Controller.getAllPageProductCategoryLevel2() invoked with pageNumber: {}, pageSize: {}, status: {}", 
				 pageNumber, pageSize, status);
		Map<String, String> searchParameters = HttpReqRespUtils.getSearchParameters(webRequest);
		return productCategoryLevel2Service.getAllPageProductCategoryLevel2(pageNumber, pageSize, status, searchParameters);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductCategoryLevel2Dto productCategoryLevel2Dto) {
		log.info("ProductCategoryLevel2Controller.save() invoked");
		return productCategoryLevel2Service.save(productCategoryLevel2Dto);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProductCategoryLevel2(@RequestBody ProductCategoryLevel2Dto productCategoryLevel2Dto) {
	    log.info("ProductCategoryLevel2Controller.updateProductCategoryLevel2() invoked");
	    return productCategoryLevel2Service.updateProductCategoryLevel2(productCategoryLevel2Dto);
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductCategoryLevel2ByName(@RequestParam("productCategoryLevel2Name") String productCategoryLevel2Name) {
	    log.info("productCategoryLevel2Controller.getProductCategoryLevel2ByName() invoked");
	    return productCategoryLevel2Service.getProductCategoryLevel2ByName(productCategoryLevel2Name);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateInvoiceStatus(@RequestParam("productCategoryLevel2Id") Integer productCategoryLevel2Id, @RequestParam("status") Boolean status) {
		log.info("ProductCategoryLevel2Controller.updateInvoiceStatus() invoked.");
		return productCategoryLevel2Service.updateProductCategoryLevel2Status(productCategoryLevel2Id, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductCategoryLevel2ById(@RequestParam("id") Integer id) {
		log.info("ProductCategoryLevel2Controller.getProductCategoryLevel2ById() invoked with id", id);
		return productCategoryLevel2Service.getProductCategoryLevel2ById(id);
	}
	
	@GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllProductCategoryLevel2(@RequestParam(value = "productCategoryLevel2Name", required = false) String productCategoryLevel2Name) {
        log.info("ProductCategoryLevel2Controller.getAllProductCategoryLevel2() invoked with productCategoryLevel2Name: {}", productCategoryLevel2Name);
        return productCategoryLevel2Service.getAllProductCategoryLevel2(productCategoryLevel2Name);
    }
	
}
