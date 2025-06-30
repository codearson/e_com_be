package com.e_com.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.e_com.Dto.ProductCategoryLevel2Dto;
import com.e_com.Dto.ProductCategoryLevel3Dto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryLevel3Service;
import com.e_com.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ProductCategoryLevel3Controller.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Mohomed Wazeem
 * @date Jun 27, 2025
 * @time 5:09:41 PM
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ProductCategoryLevel3")
public class ProductCategoryLevel3Controller {
	
	@Autowired
	ProductCategoryLevel3Service productCategoryLevel3Service;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductCategoryLevel3Dto productCategoryLevel3Dto) {
		log.info("ProductCategoryLevel3ontroller.save() invoked");
		return productCategoryLevel3Service.save(productCategoryLevel3Dto);
	}

	@GetMapping("/getAllPage")
	public ResponseDto getAllPageProductCategoryLevel3(@RequestParam("pageNumber") int pageNumber,
	                                                  @RequestParam("pageSize") int pageSize,
	                                                  @RequestParam("status") Boolean status,
	                                                  WebRequest webRequest) {
		log.info("ProductCategoryLevel3Controller.getAllPageProductCategoryLevel3() invoked with pageNumber: {}, pageSize: {}, status: {}", pageNumber, pageSize, status);
		return productCategoryLevel3Service.getAllPageProductCategoryLevel3(pageNumber, pageSize, status, HttpReqRespUtils.getSearchParameters(webRequest));
	}

	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProductCategoryLevel3(@RequestBody ProductCategoryLevel3Dto productCategoryLevel3Dto) {
		log.info("ProductCategoryLevel3Controller.updateProductCategoryLevel3() invoked");
		return productCategoryLevel3Service.updateProductCategoryLevel3(productCategoryLevel3Dto);
	}

	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductCategoryLevel3ByName(@RequestParam("productCategoryLevel3Name") String productCategoryLevel3Name) {
		log.info("ProductCategoryLevel3Controller.getProductCategoryLevel3ByName() invoked");
		return productCategoryLevel3Service.getProductCategoryLevel3ByName(productCategoryLevel3Name);
	}

	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProductCategoryLevel3Status(@RequestParam("productCategoryLevel3Id") Integer productCategoryLevel3Id, @RequestParam("status") Boolean status) {
		log.info("ProductCategoryLevel3Controller.updateProductCategoryLevel3Status() invoked.");
		return productCategoryLevel3Service.updateProductCategoryLevel3Status(productCategoryLevel3Id, status);
	}

	@GetMapping("/getById")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductCategoryLevel3ById(@RequestParam("id") Integer id) {
		log.info("ProductCategoryLevel3Controller.getProductCategoryLevel3ById() invoked with id {}", id);
		return productCategoryLevel3Service.getProductCategoryLevel3ById(id);
	}

	@GetMapping("/getAllBySearch")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllProductCategoryLevel3(@RequestParam(value = "productCategoryLevel3Name", required = false) String productCategoryLevel3Name) {
		log.info("ProductCategoryLevel3Controller.getAllProductCategoryLevel3() invoked with productCategoryLevel3Name: {}", productCategoryLevel3Name);
		return productCategoryLevel3Service.getAllProductCategoryLevel3(productCategoryLevel3Name);
	}

}
