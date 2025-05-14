package com.e_com.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.e_com.Dto.ProductCategoryDto;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.ProductCategoryService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("product-Category")
public class ProductCategoryController {
	 @Autowired
	    ProductCategoryService productCategoryService;

	    @PostMapping("/save")
	    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	    public ResponseDto saveProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
	        log.info("ProductCategoryController.saveProductCategory() invoked");
	        return productCategoryService.saveProductCategory(productCategoryDto);
}
	    }
