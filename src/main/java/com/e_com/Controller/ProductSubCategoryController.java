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

import com.e_com.Dto.ProductSubCategoryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductSubCategoryService;
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
@RequestMapping("productSubCategory")
public class ProductSubCategoryController {

	@Autowired
	ProductSubCategoryService productSubCategoryService;
	
	@GetMapping("/getAllPage")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status, WebRequest webRequest) {
		log.info("ProductSubCategoryController.getAll() invoked.");
		Map<String, String> searchParams = HttpReqRespUtils.getSearchParameters(webRequest);
		searchParams.put("status", status.toString());
		return productSubCategoryService.getAll(pageNumber, pageSize, searchParams);
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductSubCategoryDto productSubCategoryDto) {
		log.info("ProductSubCategoryController.save() invoked");
		return productSubCategoryService.save(productSubCategoryDto);
	}
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProductSubCategory(@RequestBody ProductSubCategoryDto productSubCategoryDto) {
	    log.info("ProductSubCategoryController.updateProductSubCategory() invoked");
	    return productSubCategoryService.updateProductSubCategory(productSubCategoryDto);
	}
	
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductSubCategoryByName(@RequestParam("branchName") String productSubCategoryName) {
	    log.info("ProductSubCategoryController.getProductSubCategoryByName() invoked");
	    return productSubCategoryService.getProductSubCategoryByName(productSubCategoryName);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateInvoiceStatus(@RequestParam("productSubCategoryId") Integer productSubCategoryId, @RequestParam("status") Boolean status) {
		log.info("ProductSubCategoryController.updateInvoiceStatus() invoked.");
		return productSubCategoryService.updateProductSubCategoryStatus(productSubCategoryId, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getProductSubCategoryById(@RequestParam("id") Integer id) {
		log.info("ProductSubCategoryController.getProductSubCategoryById() invoked with id", id);
		return productSubCategoryService.getProductSubCategoryById(id);
	}
	
	@GetMapping("/getAllBySearch")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllProductSubCategory(@RequestParam(value = "branchName", required = false) String productSubCategoryName) {
        log.info("ProductSubCategoryController.getAllProductSubCategory() invoked with productSubCategoryName: {}", productSubCategoryName);
        return productSubCategoryService.getAllProductSubCategory(productSubCategoryName);
    }
	
}
